package com.example.mediclinic.model;

import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "patientEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "medical_history")
    private String medicalHistory;

    @Column(name = "phone")
    private String phone;

//    @ManyToOne
//    @JoinColumn(name = "appointment_id")
//    private Appointment appointment;
}