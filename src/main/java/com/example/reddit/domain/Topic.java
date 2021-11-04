package com.example.reddit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private Set<Post> posts = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "topics")
    private Set<User> users = new HashSet<>();

    public Topic() {}

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> moderators) {
        this.users = moderators;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setTopic(this);
    }
}
