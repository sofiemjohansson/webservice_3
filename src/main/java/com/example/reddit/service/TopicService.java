package com.example.reddit.service;

import com.example.reddit.domain.Topic;
import com.example.reddit.domain.User;
import com.example.reddit.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public void create(String name) {
        if (topicRepository.findByName(name).isPresent()) {
            throw new RuntimeException("Topic already exists");
        }
        topicRepository.save(new Topic(name));
    }

    public void delete(String name) {
        if (topicRepository.findByName(name).isEmpty()) {
            throw new RuntimeException("Topic does not exist");
        }
        topicRepository.deleteByName(name);
    }

    public Topic findByName(String name) {
        return topicRepository.findByName(name).orElseThrow(() -> new RuntimeException("Topic does not exist"));
    }

    public Topic getByName(String name) {
        return topicRepository.getOneByName(name).orElseThrow(() -> new RuntimeException("Topic does not exist"));
    }

    public Set<Topic> findAll() {
        Set<Topic> topics = new HashSet<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic addTopicModerator(Topic topic, User user) {
        topic.getUsers().add(user);
        return save(topic);
    }

    public Topic removeTopicModerator(Topic topic, User user) {
        topic.getUsers().remove(user);
        return save(topic);
    }

    public Topic subscribe(Topic topic, User user) {
        topic.getUsers().add(user);
        return save(topic);
    }

    public boolean isSubscribed(Topic topic, User user) {
        return topic.getUsers().stream().filter(u -> u.getId().equals(user.getId())).count() == 1;
    }

    public Topic unsubscribe(Topic topic, User user) {
        topic.getUsers().removeIf(u -> u.getId().equals(user.getId()));
        return topicRepository.save(topic);
    }
}
