package com.example.mediclinic.service;

import com.example.mediclinic.model.Role;
import com.example.mediclinic.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Save a new Role or Update an existing one
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    // Update an existing Role by ID
    public Role update(Long id, Role role) {
        Optional<Role> existingRole = roleRepository.findById(id);
        if (existingRole.isPresent()) {
            Role updatedRole = existingRole.get();
            updatedRole.setName(role.getName()); // Update any other fields here
            return roleRepository.save(updatedRole);
        }
        throw new RuntimeException("Role not found with ID: " + id);
    }

    // Delete Role by ID
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    // Get all Roles
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    // Get Role by ID
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));
    }

    // Get Role by Name
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found with Name: " + name));
    }
}
