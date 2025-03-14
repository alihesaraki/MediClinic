package com.example.mediclinic.model;

import jakarta.persistence.*;

@Entity(name = "permissionEntity")
@Table(name = "permissions_table")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "permissionName", unique = true, nullable = false)
    private String permissionName;

    public Permission(Long id, String permissionName) {
        this.id = id;
        this.permissionName = permissionName;
    }

    public Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public Permission setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public Permission setPermissionName(String permissionName) {
        this.permissionName = permissionName;
        return this;
    }
    //.
}
