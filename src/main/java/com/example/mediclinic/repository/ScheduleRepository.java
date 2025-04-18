package com.example.mediclinic.repository;

import com.example.mediclinic.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByDoctorIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual
            (Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime );


    List<Schedule> findByDoctorNameLike(String doctorName);
}
