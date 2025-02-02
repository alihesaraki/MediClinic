package com.example.mediclinic.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @Column(name = "date_issued", nullable = false)
    private LocalDateTime dateIssued;

    // Default constructor
    public Prescription() {}

    // All-args constructor
    public Prescription(Long id, Patient patient, Medicine medicine, String dosage, LocalDateTime dateIssued) {
        this.id = id;
        this.patient = patient;
        this.medicine = medicine;
        this.dosage = dosage;
        this.dateIssued = dateIssued;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public LocalDateTime getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(LocalDateTime dateIssued) {
        this.dateIssued = dateIssued;
    }

    // toString method
    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", patient=" + patient +
                ", medicine=" + medicine +
                ", dosage='" + dosage + '\'' +
                ", dateIssued=" + dateIssued +
                '}';
    }
}
