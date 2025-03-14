package com.example.mediclinic.service;

import com.example.mediclinic.model.Prescription;
import com.example.mediclinic.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    // Save a new Prescription or Update an existing one
    public Prescription save(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    // Update an existing Prescription
    public Prescription update(Prescription prescription) {
        Optional<Prescription> existingPrescription = prescriptionRepository.findById(prescription.getId());
        if (existingPrescription.isPresent()) {
            Prescription updatedPrescription = existingPrescription.get();
//            updatedPrescription.setMedicine(prescription.getMedicine());
            updatedPrescription.setDosage(prescription.getDosage());
//            updatedPrescription.setPatient(prescription.getPatient());
            updatedPrescription.setDateTimeIssued(prescription.getDateTimeIssued());
            return prescriptionRepository.save(updatedPrescription);
        }
        throw new RuntimeException("Prescription not found with ID: " + prescription.getId());
    }

    // Delete Prescription by ID
    public void delete(Long id) {
        prescriptionRepository.deleteById(id);
    }

    // Get all Prescriptions
    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    // Get Prescription by ID
    public Prescription findById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with ID: " + id));
    }
}
