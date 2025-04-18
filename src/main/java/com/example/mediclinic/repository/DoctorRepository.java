package com.example.mediclinic.repository;

import com.example.mediclinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecializationId(Long specializationId);
    Optional<Doctor> findByDoctorCode(String doctorCode);
    Doctor findByDoctorName(String doctorName);

}
