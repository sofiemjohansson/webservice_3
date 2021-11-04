package com.example.reddit.http.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateCommentRequest {

    @NotNull
    private Long post;
    @NotBlank
    @NotNull
    private String text;

    public CreateCommentRequest() { }

    public CreateCommentRequest(@NotNull Long post, @NotBlank @NotNull String text) {
        this.post = post;
        this.text = text;
    }

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
