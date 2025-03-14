package com.example.mediclinic.controller;

import com.example.mediclinic.model.Appointment;
import com.example.mediclinic.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Create Appointment
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.save(appointment);
    }

    // Update Appointment by ID
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        Appointment updatedAppointment = appointmentService.update(id, appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    // Delete Appointment by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.delete(id);
    }

    // Get All Appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.findAll();
        return ResponseEntity.ok(appointments);
    }

    // Get Appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.findById(id);
        return ResponseEntity.ok(appointment);
    }

    // Get Appointments by Patient ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentService.findByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }

//    todo : Error
    // Get Appointments by Doctor ID
//    @GetMapping("/doctor/{doctorId}")
//    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
//        List<Appointment> appointments = appointmentService.findByDoctorId(doctorId);
//        return ResponseEntity.ok(appointments);
//    }
}
