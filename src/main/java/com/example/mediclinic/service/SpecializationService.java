package com.example.mediclinic.service;

import com.example.mediclinic.exception.ResourceNotFoundException;
import com.example.mediclinic.model.Specialization;
import com.example.mediclinic.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    // Save
    public Specialization save(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    // Update
    public Specialization update(Specialization specialization) {
        // ابتدا بررسی می‌کنیم که Specialization با این id وجود دارد یا خیر
        Optional<Specialization> existingSpecialization = specializationRepository.findById(specialization.getId());

        // اگر موجود باشد، آن را آپدیت می‌کنیم
        if (existingSpecialization.isPresent()) {
            Specialization updatedSpecialization = existingSpecialization.get();
            updatedSpecialization.setName(specialization.getName());  // فرض کنید فیلد name را آپدیت می‌کنید
            // سایر فیلدها را هم به همین ترتیب آپدیت کنید

            // ذخیره‌سازی تغییرات
            return specializationRepository.save(updatedSpecialization);
        } else {
            // اگر موجود نباشد، می‌توانید خطای مناسب را پرتاب کنید
            throw new ResourceNotFoundException("Specialization with id " + specialization.getId() + " not found.");
        }
    }

    // Delete
    public void delete(Long id) {
        specializationRepository.deleteById(id);
    }

    // FindAll
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    // FindById
    public Specialization findById(Long id) {
        return specializationRepository.findById(id).orElse(null);
    }

    // FindByName
    public Specialization findByName(String name) {
        return specializationRepository.findByName(name);
    }
}
