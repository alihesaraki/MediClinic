package com.example.mediclinic.service;

import com.example.mediclinic.exception.ResourceNotFoundException;
import com.example.mediclinic.model.MedicalHistory;
import com.example.mediclinic.repository.MedicalHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    public MedicalHistory save(MedicalHistory medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }

    public MedicalHistory update(Long id, MedicalHistory medicalHistory) {
        Optional<MedicalHistory> existingMedicalHistory = medicalHistoryRepository.findById(id);

        if (existingMedicalHistory.isPresent()) {
            MedicalHistory updatedMedicalHistory = existingMedicalHistory.get();
            updatedMedicalHistory.setPatient(medicalHistory.getPatient());

            return medicalHistoryRepository.save(updatedMedicalHistory);
        } else {
            throw new ResourceNotFoundException("Medical History with id " + id + " not found.");
        }
    }

    public void delete(Long id) {
        Optional<MedicalHistory> existingMedicalHistory = medicalHistoryRepository.findById(id);
        if (existingMedicalHistory.isPresent()) {
            medicalHistoryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Medical History with id " + id + " not found.");
        }
    }

    public List<MedicalHistory> findAll() {
        return medicalHistoryRepository.findAll();
    }

    public MedicalHistory findById(Long id) {
        return medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical History with id " + id + " not found."));
    }
}
