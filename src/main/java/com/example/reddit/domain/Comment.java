package com.example.reddit.domain;

import com.example.reddit.domain.enums.ContentStatus;
import com.example.reddit.util.serializers.CommentSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comments")
@JsonSerialize(using = CommentSerializer.class)
public class Comment extends BaseEntity {

    private String text;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Post post;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToMany(mappedBy = "parent")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Comment parent = null;

    @OneToOne(cascade = CascadeType.ALL)
    private Rating rating = new Rating(0, 0);

    @Enumerated(EnumType.STRING)
    private ContentStatus status = ContentStatus.REGULAR;

    public Comment() {}

    public Comment(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
        parent.getComments().add(this);
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }
}
