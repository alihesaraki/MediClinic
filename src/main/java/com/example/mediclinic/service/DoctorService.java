package com.example.mediclinic.service;

import com.example.mediclinic.exception.ResourceNotFoundException;
import com.example.mediclinic.model.Doctor;
import com.example.mediclinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Save
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Update
    public Doctor update(Long id, Doctor doctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(id);

        if (existingDoctor.isPresent()) {
            Doctor updatedDoctor = existingDoctor.get();
            updatedDoctor.setName(doctor.getName()); // Assume 'name' is a field to update
            updatedDoctor.setSpecialization(doctor.getSpecialization()); // Example for specialization field update

            return doctorRepository.save(updatedDoctor);
        } else {
            throw new ResourceNotFoundException("Doctor with id " + id + " not found.");
        }
    }

    // Delete
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    // FindAll
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    // FindById
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    // FindBySpecializationId
    public List<Doctor> findBySpecializationId(Long specializationId) {
        return doctorRepository.findBySpecializationId(specializationId);
    }
}
