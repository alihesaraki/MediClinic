package com.example.mediclinic.repository;

import com.example.mediclinic.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
//    @Query("select p from permissionEntity p where p.permissionName=:permissionName")
    Optional<Permission> findByPermissionName(String permissionName);
    //.
}
