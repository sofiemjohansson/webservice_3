package com.example.reddit.controllers;

import com.example.reddit.domain.User;
import com.example.reddit.http.request.LoginRequest;
import com.example.reddit.http.request.RegistrationRequest;
import com.example.reddit.http.response.LoginResponse;
import com.example.reddit.http.response.RegistrationResponse;
import com.example.reddit.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authorized");
        User user = (User) authService.loadUserByUsername(loginRequest.getUsername());
        return ResponseEntity.ok(new LoginResponse(user.getId(),loginRequest.getUsername(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        User user = authService.register(new User(registrationRequest.getUsername(), registrationRequest.getPassword(), registrationRequest.getEmail()));
        return ResponseEntity.ok(new RegistrationResponse(user.getUsername(), user.getEmail(),"User with id " + user.getId() +  " successfully created"));
    }

    
}
