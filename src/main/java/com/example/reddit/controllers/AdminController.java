package com.example.reddit.controllers;

import com.example.reddit.domain.Topic;
import com.example.reddit.domain.User;
import com.example.reddit.domain.enums.ResourceType;
import com.example.reddit.http.request.ChangeRolesRequest;
import com.example.reddit.http.response.ResourceCreatedResponse;
import com.example.reddit.http.response.ResourceDestroyedResponse;
import com.example.reddit.service.TopicService;
import com.example.reddit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    private final TopicService topicService;
    private final UserService userService;

    public AdminController(TopicService topicService, UserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @PostMapping("/topic")
    public ResponseEntity<?> createTopic(@RequestParam(name = "topic") String topicName) {
        topicService.create(topicName);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResourceCreatedResponse(ResourceType.TOPIC));
    }

    @DeleteMapping("/topic")
    public ResponseEntity<?> deleteTopic(@RequestParam(name = "topic") String topicName) {
        topicService.delete(topicName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResourceDestroyedResponse(ResourceType.TOPIC));
    }

    @PostMapping("/moderator")
    public ResponseEntity<?> setTopicModerator(@RequestParam(name = "topic") String name, @RequestParam(name = "user") String username) {
        User user = userService.getByUsername(username);
        Topic topic = topicService.getByName(name);
        topicService.addTopicModerator(topic, user);
        return ResponseEntity.ok(userService.addAsTopicModerator(user, topic));
    }

    @DeleteMapping("/moderator")
    public ResponseEntity<?> removeTopicModerator(@RequestParam(name = "topic") String name, @RequestParam(name = "user") String username) {
        User user = userService.getByUsername(username);
        Topic topic = topicService.getByName(name);
        topicService.removeTopicModerator(topic, user);
        return ResponseEntity.ok(userService.removeAsTopicModerator(user, topic));
    }

    @PostMapping("/roles")
    public ResponseEntity<?> setRoles(@Valid @RequestBody ChangeRolesRequest request) {
        User user = userService.getByUsername(request.getUsername());
        return ResponseEntity.ok(userService.changeRoles(user, request.getRoles()));
    }
}
