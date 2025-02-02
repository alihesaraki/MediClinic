package com.example.mediclinic.service;

import com.example.mediclinic.model.Schedule;
import com.example.mediclinic.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // Save a new schedule or update an existing one
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // Update an existing schedule by ID
    public Schedule update(Long id, Schedule schedule) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(id);
        if (existingSchedule.isPresent()) {
            Schedule updatedSchedule = existingSchedule.get();
            updatedSchedule.setDate(schedule.getDate());  // Update any other fields here
            updatedSchedule.setDoctor(schedule.getDoctor());  // Example of updating another field
            return scheduleRepository.save(updatedSchedule);
        }
        throw new RuntimeException("Schedule not found with ID: " + id);
    }

    // Delete schedule by ID
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    // Get all schedules
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    // Get schedule by ID
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with ID: " + id));
    }
}
