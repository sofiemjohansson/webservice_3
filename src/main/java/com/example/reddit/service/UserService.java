package com.example.reddit.service;

import com.example.reddit.domain.*;
import com.example.reddit.domain.enums.RatingType;
import com.example.reddit.domain.enums.RoleName;
import com.example.reddit.repository.RoleRepository;
import com.example.reddit.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User getByUsername(String username) {
        return userRepository.getOneByUsername(username).orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User addAsTopicModerator(User user, Topic topic) {
        user.getTopics().add(topic);
        user.getRoles().add(roleService.getRole(RoleName.ROLE_MODERATOR));
        return save(user);
    }

    public User removeAsTopicModerator(User user, Topic topic) {
        user.getTopics().remove(topic);
        if (user.getTopics().isEmpty()) {
            user.getRoles().removeIf(r -> r.getRole() == RoleName.ROLE_MODERATOR);
        }
        return save(user);
    }

    public User changeRoles(User user, Set<String> roles) {
        Set<Role> newRoles = roles.stream().map(r -> roleService.getRole(Enum.valueOf(RoleName.class, r))).collect(Collectors.toSet());
        user.setRoles(newRoles);
        return save(user);
    }

    public boolean isModerator(User user) {
        for(Role r : user.getRoles()) {
            if (r.getRole().equals(RoleName.ROLE_MODERATOR)) return true;
        }
        return false;
    }


    public User subscribe(User user, Topic topic) {
        user.getTopics().add(topic);
        return userRepository.save(user);
    }

    public User unsubscribe(User user, Topic topic) {
        user.getTopics().removeIf(t -> t.getId().equals(topic.getId()));
        return userRepository.save(user);
    }

    public boolean isUpVoted(User user, Long post, RatingType type) {
        Optional<RatingConstraint> ratingConstraint = user.getRatings().stream()
                .filter(r -> r.getPost().equals(post) && r.getType() == type).findFirst();
        if(ratingConstraint.isEmpty()) return false;
        return ratingConstraint.get().getStatus() == 1;
    }

    public boolean isDownVoted(User user, Long post, RatingType type) {
        Optional<RatingConstraint> ratingConstraint = user.getRatings().stream()
                .filter(r -> r.getPost().equals(post) && r.getType() == type).findFirst();
        if(ratingConstraint.isEmpty()) return false;
        return ratingConstraint.get().getStatus() == -1;
    }
}
