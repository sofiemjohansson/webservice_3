package com.example.reddit.repository;

import com.example.reddit.domain.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Long> {

    Optional<Topic> findByName(String name);
    Optional<Topic> getOneByName(String name);
    void deleteByName(String name);
}
