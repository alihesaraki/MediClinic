package com.example.mediclinic.service;

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

    // Save or Update Doctor
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Update Doctor by ID
    public Doctor update(Long id, Doctor doctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(id);
        if (existingDoctor.isPresent()) {
            Doctor updatedDoctor = existingDoctor.get();
            updatedDoctor.setName(doctor.getName());
            updatedDoctor.setSpecialization(doctor.getSpecialization());
            updatedDoctor.setExperienceYears(doctor.getExperienceYears());
            updatedDoctor.setContactInfo(doctor.getContactInfo());
            return doctorRepository.save(updatedDoctor);
        }
        throw new RuntimeException("Doctor not found with ID: " + id);
    }

    // Delete Doctor
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    // Get All Doctors
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    // Get Doctor by ID
    public Doctor findById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + id));
    }

    // Get Doctors by Specialization ID
    public List<Doctor> findBySpecializationId(Long specializationId) {
        return doctorRepository.findBySpecializationId(specializationId);
    }
}
