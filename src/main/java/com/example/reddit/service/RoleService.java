package com.example.reddit.service;

import com.example.reddit.domain.Role;
import com.example.reddit.domain.enums.RoleName;
import com.example.reddit.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(RoleName roleName) {
        Optional<Role> role = roleRepository.findByRole(roleName);
        return role.orElseGet(() -> roleRepository.save(new Role(roleName)));
    }
}
