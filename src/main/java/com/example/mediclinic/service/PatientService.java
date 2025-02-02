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

    // Save or Update Patient
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    // Update Patient
    public Patient update(Patient patient) {
        Optional<Patient> existingPatient = patientRepository.findById(patient.getId());
        if (existingPatient.isPresent()) {
            Patient updatedPatient = existingPatient.get();
            updatedPatient.setName(patient.getName());
            updatedPatient.setAge(patient.getAge());
            updatedPatient.setMedicalHistory(patient.getMedicalHistory());
            updatedPatient.setPhone(patient.getPhone());
            return patientRepository.save(updatedPatient);
        }
        throw new RuntimeException("Patient not found with ID: " + patient.getId());
    }

    // Delete Patient by ID
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    // Get All Patients
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    // Get Patient by ID
    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
    }
}
