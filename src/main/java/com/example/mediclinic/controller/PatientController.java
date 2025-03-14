package com.example.mediclinic.controller;

import com.example.mediclinic.model.Patient;
import com.example.mediclinic.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Create or Update Patient
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createOrUpdatePatient(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    // Update Patient by ID
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        patient.setId(id);  // Ensuring the ID is passed for update
        Patient updatedPatient = patientService.update(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    // Delete Patient by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable Long id) {
        patientService.delete(id);
    }

    // Get All Patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    // Get Patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.findById(id);
        return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<Patient> getPatientByLastName(@PathVariable String lastName) {
       List<Patient> patients = patientService.findByLastName(lastName);
        return patients != null ? ResponseEntity.ok(patients.get(0)) : ResponseEntity.notFound().build();
    }
}
