package com.example.mediclinic.user.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // Find Role by name
    Optional<Role> findByName(String name);
}
