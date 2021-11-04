package com.example.reddit.bootstrap;

import com.example.reddit.domain.Comment;
import com.example.reddit.domain.Post;
import com.example.reddit.domain.Topic;
import com.example.reddit.domain.User;
import com.example.reddit.domain.enums.RoleName;
import com.example.reddit.repository.CommentRepository;
import com.example.reddit.repository.PostRepository;
import com.example.reddit.repository.UserRepository;
import com.example.reddit.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// @Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public DataInitializer(UserRepository userRepository, RoleService roleService, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user = new User("admin","admin","admin@admin.com");
        user.getRoles().add(roleService.getRole(RoleName.ROLE_ADMIN));
        User user2 = new User("foo","foo","foo@foo.com");
        user2.getRoles().add(roleService.getRole(RoleName.ROLE_USER));
        User user3 = new User("bar","bar","bar@bar.com");
        user3.getRoles().add(roleService.getRole(RoleName.ROLE_USER));
        Topic topic = new Topic("Topic");
        Topic topic2 = new Topic("Topic 2");
        user.addTopic(topic);
        user2.addTopic(topic);
        user3.addTopic(topic2);
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        Post post = new Post("Foo", "Bar");
        post.setUser(user3);
        post.setTopic(topic2);
        Post post2 = new Post("Tell me something about yourself?", "Bar");
        post2.setUser(user2);
        post2.setTopic(topic);
        postRepository.save(post);
        postRepository.save(post2);
        Comment comment = new Comment("Comment 1");
        Comment comment2 = new Comment("Comment 2");
        comment.setUser(user2);
        comment2.setUser(user3);
        comment.setPost(post);
        comment2.setPost(post);
        commentRepository.save(comment);
        commentRepository.save(comment2);
    }
}
