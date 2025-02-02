package com.example.mediclinic.service;

import com.example.mediclinic.model.Medicine;
import com.example.mediclinic.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    // Save or Update Medicine
    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // Update Medicine by ID
    public Medicine update(Long id, Medicine medicine) {
        Optional<Medicine> existingMedicine = medicineRepository.findById(id);
        if (existingMedicine.isPresent()) {
            Medicine updatedMedicine = existingMedicine.get();
            updatedMedicine.setName(medicine.getName());
            updatedMedicine.setDescription(medicine.getDescription());
            updatedMedicine.setPrice(medicine.getPrice());
            updatedMedicine.setStock(medicine.getStock());
            return medicineRepository.save(updatedMedicine);
        }
        throw new RuntimeException("Medicine not found with ID: " + id);
    }

    // Delete Medicine by ID
    public void delete(Long id) {
        medicineRepository.deleteById(id);
    }

    // Get All Medicines
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    // Get Medicine by ID
    public Medicine findById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + id));
    }
}
