package com.example.mediclinic.service;

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

    // Save a new Appointment
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Update an existing Appointment
    public Appointment update(Long id, Appointment appointment) {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(id);
        if (existingAppointment.isPresent()) {
            Appointment updatedAppointment = existingAppointment.get();
            updatedAppointment.setPatient(appointment.getPatient());
            updatedAppointment.setDoctor(appointment.getDoctor());
            updatedAppointment.setDate(appointment.getDate());
            updatedAppointment.setTime(appointment.getTime());
            return appointmentRepository.save(updatedAppointment);
        }
        throw new RuntimeException("Appointment not found with ID: " + id);
    }

    // Delete an Appointment
    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Get all Appointments
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    // Get Appointment by ID
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
    }

    // Get Appointments by Patient ID
    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // Get Appointments by Doctor ID
    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}
