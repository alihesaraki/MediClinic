package com.example.mediclinic.service;

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

    public List<Patient> getAllPatients() {
        return patientRepository.findByDeletedFalse();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        return patientRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(updatedPatient.getFirstName());
                    existing.setLastName(updatedPatient.getLastName());
                    existing.setAge(updatedPatient.getAge());
                    existing.setPhone(updatedPatient.getPhone());
                    return patientRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    public void deletePatient(Long id) {
        patientRepository.findById(id).ifPresent(patient -> {
            patient.setDeleted(true); // Soft delete
            patientRepository.save(patient);
        });
    }

    public List<Patient> searchByLastName(String lastName) {
        return patientRepository.findByLastNameContainingIgnoreCase(lastName);
    }
}
