package com.example.reddit.service;

import com.example.reddit.domain.Post;
import com.example.reddit.domain.Topic;
import com.example.reddit.domain.User;
import com.example.reddit.repository.PostRepository;
import com.example.reddit.repository.TopicRepository;
import com.example.reddit.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, TopicRepository topicRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Post create(User user, Topic topic, String title, String text) {
        Post post = new Post(title, text);
        post.setUser(user);
        post.setTopic(topic);
        return postRepository.save(post);
    }

    public Optional<Post> get(Long id) {
        return postRepository.findById(id);
    }

    public void delete(Post post, User user, Topic topic) {
        user.getPosts().removeIf(p -> p.getId().equals(post.getId()));
        topic.getPosts().removeIf(p -> p.getId().equals(post.getId()));
        userRepository.save(user);
        topicRepository.save(topic);
        postRepository.delete(post);
    }

    public Post upVote(Post p, User user, boolean downVoted, boolean upVoted) {
        if (upVoted) {
            p.getRating().removeUpVote();
            user = user.votePost(p.getId(), 0);
        }
        else if (downVoted) {
            p.getRating().removeDownVote();
            user = user.votePost(p.getId(), 0);
        }
        else {
            p.getRating().upVote();
            user = user.votePost(p.getId(), 1);
        }
        userRepository.save(user);
        return postRepository.save(p);
    }

    public Post downVote(Post p, User user, boolean downVoted, boolean upVoted) {
        if (upVoted) {
            p.getRating().removeUpVote();
            user = user.votePost(p.getId(), 0);
        }
        else if (downVoted) {
            p.getRating().removeDownVote();
            user = user.votePost(p.getId(), 0);
        }
        else {
            p.getRating().downVote();
            user = user.votePost(p.getId(), -1);
        }
        userRepository.save(user);
        return postRepository.save(p);
    }

    public Set<Post> findAll() {
        Set<Post> posts = new HashSet<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }
}
