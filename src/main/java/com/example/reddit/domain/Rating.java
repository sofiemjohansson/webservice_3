package com.example.reddit.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

    private Integer likes = 0;
    private Integer dislikes = 0;

    public Rating() { }

    public Rating(Integer likes, Integer dislikes) {
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public void upVote() {
        likes++;
    }

    public void downVote() {
        dislikes++;
    }

    public void removeUpVote() {
        likes--;
    }

    public void removeDownVote() {
        dislikes--;
    }
}
