package com.example.mediclinic.repository;

import com.example.mediclinic.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
        List<Appointment> findByPatientId(Long patientId);
    @Query("select a from appointmentEntity a where" +
            "((:startTime between a.startDateTime and a.endDateTime) " +
            "or (:endTime between a.startDateTime and a.endDateTime))" )
    List<Appointment> findByAvailableTime(@Param("startTime") LocalDateTime startTime,@Param("endTime") LocalDateTime endTime);


}
