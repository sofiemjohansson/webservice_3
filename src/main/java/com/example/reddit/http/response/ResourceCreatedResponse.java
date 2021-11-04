package com.example.reddit.http.response;

import com.example.reddit.domain.enums.ResourceType;

public class ResourceCreatedResponse {

    private String message;

    private ResourceType resourceType;

    public ResourceCreatedResponse(ResourceType resourceType) {
        this("Resource successfully created", resourceType);
    }

    public ResourceCreatedResponse(String message, ResourceType resourceType) {
        this.message = message;
        this.resourceType = resourceType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
