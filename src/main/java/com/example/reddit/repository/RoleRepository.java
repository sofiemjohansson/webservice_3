package com.example.reddit.repository;

import com.example.reddit.domain.Role;
import com.example.reddit.domain.enums.RoleName;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRole(RoleName role);
}
