package com.example.mediclinic.prescription;

import com.example.mediclinic.medicalHistory.MedicalHistoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, MedicalHistoryRepository medicalHistoryRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @PostConstruct
    public void init() {
        log.info("Cache init...");
        final List<Prescription> departments = prescriptionRepository.findAll();
        log.info("Cache initialized...!!");
    }

    @Transactional
    @CacheEvict(cacheNames = "prescriptions", allEntries = true)
    public Prescription save(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    @Transactional
    @CacheEvict(cacheNames = "prescriptions", allEntries = true)
    public Prescription update(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    @Transactional
    @CacheEvict(cacheNames = "prescriptions", allEntries = true)
    public void logicalRemove(Long id) {
        prescriptionRepository.logicalRemove(id);
    }

    @Transactional
    @CacheEvict(cacheNames = "prescriptions", allEntries = true)
    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    @Transactional
    @CacheEvict(cacheNames = "prescriptions", allEntries = true)
    public Prescription findById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with ID: " + id));
    }

    @Transactional
    @CacheEvict(cacheNames = "prescriptions", allEntries = true)
    public List<Prescription> findByMedicalHistory(Long id) {
        return prescriptionRepository.findByMedicalHistory(id);
    }

    @Transactional
    @CacheEvict(cacheNames = "prescriptions", allEntries = true)
    public List<Prescription> findByMedicineId(Long id) {
        return prescriptionRepository.findByMedicineId(id);
    }

    @Transactional
    @CacheEvict(cacheNames = "prescriptions", allEntries = true)
    public List<Prescription> findPrescriptionInDateRange(LocalDate startDate, LocalDate endDate) {
        return prescriptionRepository.findPrescriptionInDateRange(startDate, endDate);
    }

}
