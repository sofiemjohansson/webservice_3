package com.example.reddit.service;

import com.example.reddit.domain.Comment;
import com.example.reddit.domain.Post;
import com.example.reddit.domain.User;
import com.example.reddit.domain.enums.ContentStatus;
import com.example.reddit.domain.enums.RatingType;
import com.example.reddit.http.response.GenericResponse;
import com.example.reddit.repository.CommentRepository;
import com.example.reddit.repository.PostRepository;
import com.example.reddit.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Transactional
    public Comment create(String text, User user, Post post) {
        Comment comment = new Comment(text);
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment delete(Comment comment) {
        comment.setStatus(ContentStatus.DELETED);
        comment.setText("[deleted]");
        return findParent(commentRepository.save(comment));
    }

    @Transactional
    public Comment reply(String text, Comment parent, User user, Post post) {
        Comment comment = new Comment(text);
        comment.setParent(parent);
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Comment upVote(Comment comment, User user, boolean downVoted, boolean upVoted) {
        if (downVoted) {
            comment.getRating().removeDownVote();
            user = user.voteComment(comment.getId(), 0);
        }
        else if (upVoted) {
            comment.getRating().removeUpVote();
            user = user.voteComment(comment.getId(), 0);
        }
        else {
            comment.getRating().upVote();
            user = user.voteComment(comment.getId(), 1);
        }
        userRepository.save(user);
        return commentRepository.save(comment);
    }

    public Comment downVote(Comment comment, User user, boolean downVoted, boolean upVoted) {
        if(upVoted) {
            comment.getRating().removeUpVote();
            user = user.voteComment(comment.getId(), 0);
        }
        else if (downVoted) {
            comment.getRating().removeDownVote();
            user = user.voteComment(comment.getId(), 0);
        }
        else {
            comment.getRating().downVote();
            user = user.voteComment(comment.getId(), -1);
        }
        userRepository.save(user);
        return commentRepository.save(comment);
    }

    public Comment tryUpVote(Long id, User user) {
        Optional<Comment> comment = findById(id);
        if (comment.isEmpty()) return null;
        boolean upVoted = userService.isUpVoted(user, comment.get().getId(), RatingType.COMMENT);
        boolean downVoted = userService.isDownVoted(user, comment.get().getId(), RatingType.COMMENT);
        Comment upVotedComment = upVote(comment.get(), user, downVoted, upVoted);
        return findParent(upVotedComment);
    }

    public Comment tryDownVote(Long id, User user) {
        Optional<Comment> comment = findById(id);
        if (comment.isEmpty()) return null;
        boolean upVoted = userService.isUpVoted(user, comment.get().getId(), RatingType.COMMENT);
        boolean downVoted = userService.isDownVoted(user, comment.get().getId(), RatingType.COMMENT);
        Comment downVotedComment = downVote(comment.get(), user, downVoted, upVoted);
        return findParent(downVotedComment);
    }

    private Comment findParent(Comment comment) {
        Comment parent = comment;
        while (parent.getParent() != null) parent = parent.getParent();
        return parent;
    }
}
