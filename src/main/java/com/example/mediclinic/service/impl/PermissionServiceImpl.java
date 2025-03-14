package com.example.mediclinic.service.impl;

import com.example.mediclinic.model.Permission;
import com.example.mediclinic.repository.PermissionRepository;
import com.example.mediclinic.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Transactional
    @Override
    public Permission update(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Permission> findByPermissionName(String permissionName) {
        return permissionRepository.findByPermissionName(permissionName);
    }
    //.
}
