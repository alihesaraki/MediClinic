package com.example.mediclinic.service;

import com.example.mediclinic.exception.ResourceNotFoundException;
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

    // Save
    public Prescription save(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    // Update
    public Prescription update(Prescription prescription) {
        // ابتدا بررسی می‌کنیم که Prescription با این id وجود دارد یا خیر
        Optional<Prescription> existingPrescription = prescriptionRepository.findById(prescription.getId());

        // اگر موجود باشد، آن را آپدیت می‌کنیم
        if (existingPrescription.isPresent()) {
            Prescription updatedPrescription = existingPrescription.get();
            // ذخیره‌سازی تغییرات
            return prescriptionRepository.save(updatedPrescription);
        } else {
            // اگر موجود نباشد، می‌توانید خطای مناسب را پرتاب کنید
            throw new ResourceNotFoundException("Prescription with id " + prescription.getId() + " not found.");
        }
    }

    // Delete
    public void delete(Long id) {
        prescriptionRepository.deleteById(id);
    }

    // FindAll
    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    // FindById
    public Prescription findById(Long id) {
        return prescriptionRepository.findById(id).orElse(null);
    }
}
