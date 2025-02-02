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
    public Schedule update(Schedule schedule) {
        // ابتدا بررسی می‌کنیم که Schedule با این id وجود دارد یا خیر
        Optional<Schedule> existingSchedule = scheduleRepository.findById(schedule.getId());

        // اگر موجود باشد، آن را آپدیت می‌کنیم
        if (existingSchedule.isPresent()) {
            Schedule updatedSchedule = existingSchedule.get();
            // ذخیره‌سازی تغییرات
            return scheduleRepository.save(updatedSchedule);
        } else {
            // اگر موجود نباشد، می‌توانید خطای مناسب را پرتاب کنید
            throw new ResourceNotFoundException("Schedule with id " + schedule.getId() + " not found.");
        }
    }

    // Delete
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    // FindAll
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    // FindById
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }
}
