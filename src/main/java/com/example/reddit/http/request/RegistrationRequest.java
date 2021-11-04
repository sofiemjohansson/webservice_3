package com.example.reddit.http.request;

import javax.validation.constraints.NotBlank;

public class RegistrationRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    public RegistrationRequest() {}

    public RegistrationRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
