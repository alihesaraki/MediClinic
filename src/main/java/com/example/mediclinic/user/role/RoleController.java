package com.example.mediclinic.user.role;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Create or Update Role
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role createOrUpdateRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    // Update Role by ID
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Role updatedRole = roleService.update(id, role);
        return ResponseEntity.ok(updatedRole);
    }

    // Delete Role by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable Long id) {
        roleService.delete(id);
    }

    // Get All Roles
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    // Get Role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = roleService.findById(id);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
    }

    // Get Role by Name
    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        Role role = roleService.findByName(name);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
    }
}
