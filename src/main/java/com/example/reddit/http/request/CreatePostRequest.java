package com.example.reddit.http.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreatePostRequest {

    @NotBlank
    @Size(max = 255)
    private String title;
    @NotBlank
    @NotNull
    private String text;

    public CreatePostRequest() { }

    public CreatePostRequest(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
