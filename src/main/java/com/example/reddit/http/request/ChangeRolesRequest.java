package com.example.reddit.http.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class ChangeRolesRequest {

    @NotNull
    @NotBlank
    private String username;
    @NotEmpty
    @NotNull
    private Set<String> roles = new HashSet<>();

    public ChangeRolesRequest() { }

    public ChangeRolesRequest(@NotNull @NotBlank String username, @NotEmpty @NotNull Set<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
