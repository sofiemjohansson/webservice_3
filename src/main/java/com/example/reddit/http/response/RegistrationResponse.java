package com.example.reddit.http.response;

public class RegistrationResponse {

    private String username;
    private String mail;
    private String message;

    public RegistrationResponse(String username, String mail, String message) {
        this.username = username;
        this.mail = mail;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

