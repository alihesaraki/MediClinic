package com.example.mediclinic.service;

import com.example.mediclinic.model.Patient;
import com.example.mediclinic.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
public class PatientService {
    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostConstruct
    public void init() {
        log.info("Cache init...");
        final List<Patient> patients = patientRepository.findAll();
        log.info("Cache initialized In PatientService...!!");
    }


    @Transactional
    @CacheEvict(cacheNames = "patients", allEntries = true)
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    @CacheEvict(cacheNames = "patients", allEntries = true)
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    @CacheEvict(cacheNames = "patients", allEntries = true)
    public void logicalRemove(Long patientId) {
        patientRepository.logicalRemove(patientId);
    }

    @Transactional
    @CacheEvict(cacheNames = "patients", allEntries = true)
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Transactional
    @CacheEvict(cacheNames = "patients", allEntries = true)
    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
    }

    @Transactional
    @CacheEvict(cacheNames = "patients", allEntries = true)
    public List<Patient> findByLastName(String lastName) {
        return patientRepository.findByLastName(lastName);
    }

    @Transactional
    @CacheEvict(cacheNames = "patients", allEntries = true)
    public List<Patient> findByAppointment(Long id) {
        return patientRepository.findByAppointment(id);
    }
}
