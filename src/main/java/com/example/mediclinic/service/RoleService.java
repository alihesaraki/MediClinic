package com.example.mediclinic.service;

import com.example.mediclinic.exception.ResourceNotFoundException;
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

    // Save
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    // Update
    public Role update(Long id, Role role) {
        Optional<Role> existingRole = roleRepository.findById(id);

        if (existingRole.isPresent()) {
            Role updatedRole = existingRole.get();
            updatedRole.setName(role.getName());

            return roleRepository.save(updatedRole);
        } else {
            throw new ResourceNotFoundException("Role with id " + id + " not found.");
        }
    }

    // Delete
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    // FindAll
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    // FindById
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    // FindByName
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
