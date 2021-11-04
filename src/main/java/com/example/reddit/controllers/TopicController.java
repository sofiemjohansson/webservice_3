package com.example.reddit.controllers;

import com.example.reddit.domain.Topic;
import com.example.reddit.domain.User;
import com.example.reddit.http.response.GenericResponse;
import com.example.reddit.service.TopicService;
import com.example.reddit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private final TopicService topicService;
    private final UserService userService;

    public TopicController(TopicService topicService, UserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getTopicByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(topicService.findByName(name));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<?> getAllTopics() {
        return ResponseEntity.ok(topicService.findAll());
    }

    @GetMapping("/{name}/users")
    @Secured("ROLE_USER")
    public ResponseEntity<?> getTopicUsers(@PathVariable(name = "name") String name, Principal principal) {
        Topic topic = topicService.getByName(name);
        User user = userService.getByUsername(principal.getName());
        if (!topicService.isSubscribed(topic, user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Not authorized to see content"));
        }
        return ResponseEntity.ok(topic.getUsers());
    }


    @GetMapping("/{name}/moderators")
    @Secured("ROLE_USER")
    public ResponseEntity<?> getTopicModerators(@PathVariable(name = "name") String name, Principal principal) {
        Topic topic = topicService.getByName(name);
        User user = userService.getByUsername(principal.getName());
        if (!topicService.isSubscribed(topic, user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Not authorized to see content"));
        }
        return ResponseEntity.ok(topic.getUsers().stream().filter(userService::isModerator).collect(Collectors.toSet()));
    }

    @PostMapping("/subscribe")
    @Secured("ROLE_USER")
    public ResponseEntity<?> subscribe(@RequestParam(name = "topic") String name, Principal principal) {
        Topic topic = topicService.getByName(name);
        User user = userService.getByUsername(principal.getName());
        if (topicService.isSubscribed(topic, user)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("You are already subscribed"));
        }
        topicService.subscribe(topic, user);
        user = userService.subscribe(user, topic);
        return ResponseEntity.ok(user.getTopics());
    }

    @PostMapping("/unsubscribe")
    @Secured("ROLE_USER")
    public ResponseEntity<?> unsubscribe(@RequestParam(name = "topic") String name, Principal principal) {
        Topic topic = topicService.getByName(name);
        User user = userService.getByUsername(principal.getName());
        if (!topicService.isSubscribed(topic, user)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("You are not subscribed to this topic"));
        }
        topicService.unsubscribe(topic, user);
        user = userService.unsubscribe(user, topic);
        return ResponseEntity.ok(user.getTopics());
    }
}
