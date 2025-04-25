package com.example.mediclinic.doctor;

import com.example.mediclinic.specialization.Specialization;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;

    @Column(name = "experience_years", nullable = false)
    private int experienceYears;

    @Column(name = "contact_info", nullable = false)
    private String contactInfo;
}