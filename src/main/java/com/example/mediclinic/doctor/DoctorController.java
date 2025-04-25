package com.example.mediclinic.doctor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Create or Update Doctor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor createOrUpdateDoctor(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }

    // Update Doctor by ID
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.update(id, doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    // Delete Doctor by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.delete(id);
    }

    // Get All Doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.findAll();
        return ResponseEntity.ok(doctors);
    }

    // Get Doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.findById(id);
        return doctor != null ? ResponseEntity.ok(doctor) : ResponseEntity.notFound().build();
    }

    // Get Doctors by Specialization ID
    @GetMapping("/specialization/{specializationId}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecializationId(@PathVariable Long specializationId) {
        List<Doctor> doctors = doctorService.findBySpecializationId(specializationId);
        return doctors.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(doctors);
    }
}
