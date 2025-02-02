package com.example.mediclinic.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String medicalLicenseNumber; // شماره نظام پزشکی

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // ارتباط با User برای پزشکان
}
