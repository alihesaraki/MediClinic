package com.example.mediclinic.repository;

import com.example.mediclinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Find patients by last name (or partial match)
    List<Patient> findByLastNameContainingIgnoreCase(String lastName);

    // Optional: find by phone number
    Patient findByPhone(String phone);

    // Optional: find all non-deleted patients
    List<Patient> findByDeletedFalse();
}
