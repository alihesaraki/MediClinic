package com.example.mediclinic.service;

import com.example.mediclinic.model.Appointment;
import com.example.mediclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Create a new appointment
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Update an existing appointment
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id)
                .map(existingAppointment -> {
                    existingAppointment.setStartDateTime(updatedAppointment.getStartDateTime());
                    existingAppointment.setEndDateTime(updatedAppointment.getEndDateTime());
                    existingAppointment.setPatient(updatedAppointment.getPatient());
                    return appointmentRepository.save(existingAppointment);
                })
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    // Delete an appointment by ID
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Get appointments by patient ID
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientPatientId(patientId);
    }

    // Get appointments in a time range
    public List<Appointment> getAppointmentsInRange(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByStartDateTimeBetween(start, end);
    }
}
