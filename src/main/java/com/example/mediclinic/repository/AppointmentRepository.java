package com.example.mediclinic.repository;

import com.example.mediclinic.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find appointments by patient ID
    List<Appointment> findByPatientPatientId(Long patientId);

    // Find appointments within a time range
    List<Appointment> findByStartDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
