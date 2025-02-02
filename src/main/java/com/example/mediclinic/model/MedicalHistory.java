package com.example.mediclinic.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diseaseHistory;
    private String surgeryHistory;
    private String allergies;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
