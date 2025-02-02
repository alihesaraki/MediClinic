package com.example.mediclinic.service;

import com.example.mediclinic.exception.ResourceNotFoundException;
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

    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public Medicine update(Long id, Medicine medicine) {
        Optional<Medicine> existingMedicine = medicineRepository.findById(id);

        if (existingMedicine.isPresent()) {
            Medicine updatedMedicine = existingMedicine.get();
            updatedMedicine.setName(medicine.getName());
            updatedMedicine.setDescription(medicine.getDescription());

            return medicineRepository.save(updatedMedicine);
        } else {
            throw new ResourceNotFoundException("Medicine with id " + id + " not found.");
        }
    }

    public void delete(Long id) {
        Optional<Medicine> existingMedicine = medicineRepository.findById(id);
        if (existingMedicine.isPresent()) {
            medicineRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Medicine with id " + id + " not found.");
        }
    }

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public Medicine findById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine with id " + id + " not found."));
    }
}
