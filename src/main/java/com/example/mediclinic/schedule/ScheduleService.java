package com.example.mediclinic.schedule;

import com.example.mediclinic.doctor.Doctor;
import com.example.mediclinic.doctor.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;
    private final TransactionalOperator transactionalOperator;

    public ScheduleService(ScheduleRepository scheduleRepository, DoctorRepository doctorRepository, TransactionalOperator transactionalOperator) {
        this.scheduleRepository = scheduleRepository;
        this.doctorRepository = doctorRepository;
        this.transactionalOperator = transactionalOperator;
    }

    @Transactional
    public Schedule createSchedule(String doctorCode, LocalDateTime startDateTime, LocalDateTime endDateTime, int appointmentDuration) throws Exception {
        Optional<Doctor> doctorOpt = doctorRepository.findByDoctorCode(doctorCode);
        if (doctorOpt.isEmpty()) {
            throw new Exception("Doctor not found: " + doctorCode);
        }
        if (startDateTime.isAfter(endDateTime)){
            throw new Exception("Start time must be before end time");
        }

        Schedule schedule = Schedule.builder()
                .doctor(doctorOpt.get())
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .appointmentDurationMin(appointmentDuration)
                .build();

        return scheduleRepository.save(schedule);
    }


    public Optional<Schedule>findScheduleByDoctorCodeAndDate(String doctorCode, LocalDate date) {

        Optional<Doctor> doctorOpt = doctorRepository.findByDoctorCode(doctorCode);
        if (doctorOpt.isEmpty()) {
            return Optional.empty();
        }
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return scheduleRepository.findByDoctorIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(doctorOpt.get().getId(), endOfDay, startOfDay);
    }

    public List<Schedule> findByDoctorNameLike(String doctorName){
        return scheduleRepository.findByDoctorNameLike(doctorName);
    }

    // Update an existing schedule by ID
    public Schedule update(Long id, Schedule schedule) throws Exception {
        Optional<Schedule> ScheduleOpt = scheduleRepository.findById(id);
        if (!ScheduleOpt.isEmpty()) {
            Schedule updatedSchedule = ScheduleOpt.get();
//            Todo : Error
//            updatedSchedule.setDate(schedule.getDate());  // Update any other fields here
//            updatedSchedule.setDoctor(schedule.getDoctor());  // Example of updating another field
            return scheduleRepository.save(updatedSchedule);
        }
        throw new Exception("Schedule not found with ID: " + id);
    }


    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }


    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }


    public Schedule findById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with ID: " + id));
    }
}
