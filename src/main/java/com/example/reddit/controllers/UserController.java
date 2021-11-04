package com.example.reddit.controllers;

import com.example.reddit.domain.Post;
import com.example.reddit.domain.RatingConstraint;
import com.example.reddit.domain.Topic;
import com.example.reddit.domain.User;
import com.example.reddit.domain.enums.RatingType;
import com.example.reddit.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Secured("ROLE_USER")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/post/votes")
    public Set<RatingConstraint> getPostVotes(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        return user.getRatings().stream().filter(r -> r.getType() == RatingType.POST && r.getStatus() != 0).collect(Collectors.toSet());
    }

    @GetMapping("/comment/votes")
    public Set<RatingConstraint> getCommentVotes(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        return user.getRatings().stream().filter(r -> r.getType() == RatingType.COMMENT && r.getStatus() != 0).collect(Collectors.toSet());
    }

    @GetMapping("/topics")
    public Set<Topic> getTopic(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        return user.getTopics();
    }

    @GetMapping("/posts")
    public Set<Post> getPosts(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        return user.getPosts();
    }

    @GetMapping("/subscribed")
    public Set<Post> getSubscribedPosts(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Set<Post> posts = new HashSet<>();
        user.getTopics().forEach(t -> posts.addAll(t.getPosts()));
        return posts;
    }
}
