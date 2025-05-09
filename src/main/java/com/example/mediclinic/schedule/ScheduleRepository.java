package com.example.mediclinic.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
// todo : schedule can be in two days
    Optional<Schedule> findByDoctorIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual
            (Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime );


    List<Schedule> findByDoctorNameLike(String doctorName);
}
