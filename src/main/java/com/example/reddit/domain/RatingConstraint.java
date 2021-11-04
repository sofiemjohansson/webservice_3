package com.example.reddit.domain;

import com.example.reddit.domain.enums.RatingType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "rating_constraints")
public class RatingConstraint extends BaseEntity {

    private Long post;
    private Integer status = 0;

    @Enumerated(EnumType.STRING)
    private RatingType type;

    public RatingConstraint() { }

    public RatingConstraint(Long post, Integer status, RatingType type) {
        this.post = post;
        this.status = status;
        this.type = type;
    }

    public Long getPost() {
        return post;
    }

    public void setPost(Long postId) {
        this.post = postId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RatingType getType() {
        return type;
    }

    public void setType(RatingType type) {
        this.type = type;
    }
}
