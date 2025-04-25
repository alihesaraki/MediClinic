package com.example.mediclinic.medicalHistory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    // Save or Update Medical History
    public MedicalHistory save(MedicalHistory medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }

    // Update Medical History by ID
    public MedicalHistory update(Long id, MedicalHistory medicalHistory) {
        Optional<MedicalHistory> existingHistory = medicalHistoryRepository.findById(id);
        if (existingHistory.isPresent()) {
            MedicalHistory updatedHistory = existingHistory.get();
            updatedHistory.setPatient(medicalHistory.getPatient());
            updatedHistory.setDiagnosis(medicalHistory.getDiagnosis());
            updatedHistory.setTreatment(medicalHistory.getTreatment());
            updatedHistory.setDate(medicalHistory.getDate());
            return medicalHistoryRepository.save(updatedHistory);
        }
        throw new RuntimeException("Medical History not found with ID: " + id);
    }

    // Delete Medical History
    public void delete(Long id) {
        medicalHistoryRepository.deleteById(id);
    }

    // Get All Medical Histories
    public List<MedicalHistory> findAll() {
        return medicalHistoryRepository.findAll();
    }

    // Get Medical History by ID
    public MedicalHistory findById(Long id) {
        return medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical History not found with ID: " + id));
    }
}
