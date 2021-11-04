package com.example.reddit.repository;

import com.example.reddit.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findById(Long id);
}
