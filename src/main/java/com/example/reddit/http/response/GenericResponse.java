package com.example.reddit.http.response;

public class GenericResponse {

    private String response;

    public GenericResponse() { }

    public GenericResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
