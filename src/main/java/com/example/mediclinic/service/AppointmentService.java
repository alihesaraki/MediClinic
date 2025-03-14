package com.example.mediclinic.service;

import com.example.mediclinic.model.Appointment;
import com.example.mediclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return appointmentRepository.findById(id)
                .map(existingAppointment -> {
                    existingAppointment.setPatient(appointment.getPatient());
//                    todo : Error
//                    existingAppointment.setDoctor(appointment.getDoctor());
//                    existingAppointment.setDate(appointment.getDate());
//                    existingAppointment.setTime(appointment.getTime());
                    return appointmentRepository.save(existingAppointment);
                })
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
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

//    todo : Error
    // Get Appointments by Doctor ID
//    public List<Appointment> findByDoctorId(Long doctorId) {
//        return appointmentRepository.findByDoctorId(doctorId);
//    }
}
