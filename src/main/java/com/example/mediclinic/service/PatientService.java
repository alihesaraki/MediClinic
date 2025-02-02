package com.example.mediclinic.service;

import com.example.mediclinic.exception.ResourceNotFoundException;
import com.example.mediclinic.model.Patient;
import com.example.mediclinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Save
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    // Update
    public Patient update(Patient patient) {

        Optional<Patient> existingPatient = patientRepository.findById(patient.getId());

        if (existingPatient.isPresent()) {
            Patient updatedPatient = existingPatient.get();
            updatedPatient.setName(patient.getName());

            return patientRepository.save(updatedPatient);
        } else {
            // اگر موجود نباشد، می‌توانید خطای مناسب را پرتاب کنید
            throw new ResourceNotFoundException("Patient with id " + patient.getId() + " not found.");
        }
    }

    // Delete
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    // FindAll
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    // FindById
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
}
