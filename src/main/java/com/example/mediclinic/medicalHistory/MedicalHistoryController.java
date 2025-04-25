package com.example.mediclinic.medicalHistory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    // Create or Update Medical History
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalHistory createOrUpdateMedicalHistory(@RequestBody MedicalHistory medicalHistory) {
        return medicalHistoryService.save(medicalHistory);
    }

    // Update Medical History by ID
    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistory> updateMedicalHistory(@PathVariable Long id, @RequestBody MedicalHistory medicalHistory) {
        medicalHistory.setId(id);  // Ensuring the ID is passed for update
        MedicalHistory updatedMedicalHistory = medicalHistoryService.update(id, medicalHistory);
        return ResponseEntity.ok(updatedMedicalHistory);
    }

    // Delete Medical History by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicalHistory(@PathVariable Long id) {
        medicalHistoryService.delete(id);
    }

    // Get All Medical Histories
    @GetMapping
    public ResponseEntity<List<MedicalHistory>> getAllMedicalHistories() {
        List<MedicalHistory> medicalHistories = medicalHistoryService.findAll();
        return ResponseEntity.ok(medicalHistories);
    }

    // Get Medical History by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getMedicalHistoryById(@PathVariable Long id) {
        MedicalHistory medicalHistory = medicalHistoryService.findById(id);
        return medicalHistory != null ? ResponseEntity.ok(medicalHistory) : ResponseEntity.notFound().build();
    }
}
