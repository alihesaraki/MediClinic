package com.example.mediclinic.service;

import com.example.mediclinic.model.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    Permission save(Permission permission);

    Permission update(Permission permission);

    void deleteById(Long id);

    Permission findById(Long id);

    List<Permission> findAll();

    Optional<Permission> findByPermissionName(String permissionName);

    //.


}
