package com.example.mediclinic.specialization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {

    private final SpecializationService specializationService;

    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    // Create or Update Specialization
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Specialization createOrUpdateSpecialization(@RequestBody Specialization specialization) {
        return specializationService.save(specialization);
    }

    // Update Specialization by ID
    @PutMapping("/{id}")
    public ResponseEntity<Specialization> updateSpecialization(@PathVariable Long id, @RequestBody Specialization specialization) {
        specialization.setId(id);  // Ensuring the ID is passed for update
        Specialization updatedSpecialization = specializationService.update(specialization);
        return ResponseEntity.ok(updatedSpecialization);
    }

    // Delete Specialization by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpecialization(@PathVariable Long id) {
        specializationService.delete(id);
    }

    // Get All Specializations
    @GetMapping
    public ResponseEntity<List<Specialization>> getAllSpecializations() {
        List<Specialization> specializations = specializationService.findAll();
        return ResponseEntity.ok(specializations);
    }

    // Get Specialization by ID
    @GetMapping("/{id}")
    public ResponseEntity<Specialization> getSpecializationById(@PathVariable Long id) {
        Specialization specialization = specializationService.findById(id);
        return specialization != null ? ResponseEntity.ok(specialization) : ResponseEntity.notFound().build();
    }

    // Get Specialization by Name
    @GetMapping("/name/{name}")
    public ResponseEntity<Specialization> getSpecializationByName(@PathVariable String name) {
        Specialization specialization = specializationService.findByName(name);
        return specialization != null ? ResponseEntity.ok(specialization) : ResponseEntity.notFound().build();
    }
}
