package com.example.mediclinic.service;

import com.example.mediclinic.exception.ResourceNotFoundException;
import com.example.mediclinic.model.Appointment;
import com.example.mediclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Save
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Update
    public Appointment update(Long id, Appointment appointment) {
        // ابتدا بررسی می‌کنیم که Appointment با این id وجود دارد یا خیر
        Optional<Appointment> existingAppointment = appointmentRepository.findById(id);

        // اگر موجود باشد، آن را آپدیت می‌کنیم
        if (existingAppointment.isPresent()) {
            Appointment updatedAppointment = existingAppointment.get();
            // فرض کنید که می‌خواهید فیلدهای خاصی را آپدیت کنید
            updatedAppointment.setDoctor(appointment.getDoctor());
            updatedAppointment.setPatient(appointment.getPatient());

            // ذخیره‌سازی تغییرات
            return appointmentRepository.save(updatedAppointment);
        } else {
            // اگر موجود نباشد، می‌توانید خطای مناسب را پرتاب کنید
            throw new ResourceNotFoundException("Appointment with id " + id + " not found.");
        }
    }

    // Delete
    public void delete(Long id) {
        // ابتدا بررسی می‌کنیم که Appointment با این id وجود دارد یا خیر
        Optional<Appointment> existingAppointment = appointmentRepository.findById(id);
        if (existingAppointment.isPresent()) {
            appointmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Appointment with id " + id + " not found.");
        }
    }

    // FindAll
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    // FindById
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment with id " + id + " not found."));
    }

    // FindByPatientId
    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // FindByDoctorId
    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}
