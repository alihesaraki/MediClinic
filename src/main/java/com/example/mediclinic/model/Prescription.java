package com.example.mediclinic.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "prescriptionEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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

    @ManyToOne
    @JoinColumn(name = "medical_id")
    private MedicalHistory medicalHistory;
}