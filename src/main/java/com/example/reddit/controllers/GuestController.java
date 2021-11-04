package com.example.reddit.controllers;

import com.example.reddit.domain.Post;
import com.example.reddit.http.response.GenericResponse;
import com.example.reddit.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

    private final PostService postService;

    public GuestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/post/{id}/comments")
    public ResponseEntity<?> getPostComments(@PathVariable(name = "id") Long id) {
        Optional<Post> p = postService.get(id);
        if (p.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponse("Post does not exist"));
        }
        return ResponseEntity.ok(p.get().getComments());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPostById(@PathVariable(name = "id") Long id) {
        Optional<Post> p = postService.get(id);
        if (p.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponse("Post does not exist"));
        }
        Post post = p.get();
        post.setComments(post.getComments().stream().filter(c -> c.getParent() == null).collect(Collectors.toSet()));
        return ResponseEntity.ok(post);
    }
}
