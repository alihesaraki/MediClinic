package com.example.mediclinic.specialization;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    // Save or update specialization
    public Specialization save(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    // Update an existing specialization
    public Specialization update(Specialization specialization) {
        Optional<Specialization> existingSpecialization = specializationRepository.findById(specialization.getId());
        if (existingSpecialization.isPresent()) {
            return specializationRepository.save(specialization);
        }
        throw new RuntimeException("Specialization not found with ID: " + specialization.getId());
    }

    // Delete specialization by ID
    public void delete(Long id) {
        specializationRepository.deleteById(id);
    }

    // Get all specializations
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    // Get specialization by ID
    public Specialization findById(Long id) {
        return specializationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization not found with ID: " + id));
    }

    // Get specialization by Name
    public Specialization findByName(String name) {
        return specializationRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Specialization not found with name: " + name));
    }
}
