package com.example.mediclinic.controller;


import com.example.mediclinic.model.Patient;
import com.example.mediclinic.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        patientService.save(patient);
        return ResponseEntity.status(HttpStatus.OK).body(patient);

    }

    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
        patientService.update(patient);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @DeleteMapping("/{patientId}")
    @Secured("ROLE_ADMIN")
    public void deletePatient(@PathVariable Long patientId) {
        patientService.logicalRemove(patientId);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/patientId/{patientId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patientId) {
        Patient patient = patientService.findById(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @GetMapping("/lastName/{lastName}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Patient>> getPatientByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(patientService.findByLastName(lastName));
    }

    @GetMapping("/appointmentId/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Patient>> getPatientByAppointmentId(@PathVariable Long id) {
        List<Patient> patientAppoint = patientService.findByAppointment(id);
        return ResponseEntity.ok(Collections.singletonList(patientAppoint.get(0)));
    }
}
