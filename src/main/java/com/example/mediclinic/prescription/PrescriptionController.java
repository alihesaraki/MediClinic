package com.example.mediclinic.prescription;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.findAll());
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        Prescription prescription = prescriptionService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(prescription);
    }

    @GetMapping("/medical/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Prescription>> getPrescriptionByMedicalHistoryId(@PathVariable Long id) {

        List<Prescription> prescriptions = prescriptionService.findByMedicalHistory(id);

        if (prescriptions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(prescriptions);
        }

        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/medicine/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Prescription>> getPrescriptionByMedicineId(@PathVariable Long id) {

        List<Prescription> prescriptions = prescriptionService.findByMedicineId(id);

        if (prescriptions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(prescriptions);
        }

        return ResponseEntity.ok(prescriptions);
    }

    @PostMapping("/savePrescription")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        prescriptionService.save(prescription);
        return ResponseEntity.ok(prescriptionService.save(prescription));
    }

    @PutMapping("/updatePrescription")
    public ResponseEntity<Prescription> updatePrescription(@RequestBody Prescription prescription) {
        prescriptionService.update(prescription);
        return ResponseEntity.status(HttpStatus.OK).body(prescription);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.logicalRemove(id);
    }

    @GetMapping("/between-dates/{start}/{end}")
    public ResponseEntity<List<Prescription>> getPrescriptionsBetweenDates(
            @PathVariable("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @PathVariable("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        List<Prescription> prescriptions = prescriptionService.findPrescriptionInDateRange(start, end);
        return ResponseEntity.ok(prescriptions);
    }
}
