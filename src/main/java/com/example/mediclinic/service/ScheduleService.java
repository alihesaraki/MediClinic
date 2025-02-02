package com.example.mediclinic.service;

import com.example.mediclinic.exception.ResourceNotFoundException;
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

    // Save
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // Update
    public Schedule update(Long id, Schedule schedule) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(id);

        if (existingSchedule.isPresent()) {
            Schedule updatedSchedule = existingSchedule.get();

            // به‌روزرسانی فیلدهای مورد نظر
            updatedSchedule.setDoctor(schedule.getDoctor());

            return scheduleRepository.save(updatedSchedule);
        } else {
            throw new ResourceNotFoundException("Schedule with id " + id + " not found.");
        }
    }

    // Delete
    public void delete(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Schedule with id " + id + " not found.");
        }
        scheduleRepository.deleteById(id);
    }

    // FindAll
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    // FindById
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule with id " + id + " not found."));
    }
}
