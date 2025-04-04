package com.example.mediclinic.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Table(name = "prescriptions", indexes = {
        @Index(name = "idx_prescription_date", columnList = "date_issued")})
@Entity(name = "prescriptionEntity")

@Cacheable
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@SQLRestriction("deleted = false")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private Long id;

    @Column(name = "dosage")
    private String dosage;


    @Column(name = "date_issued")
    private LocalDate dateIssued;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Medicine> medicines;

    @Column(name = "deleted")
    private boolean deleted;
    //mappedBy = "prescriptions",
    @ManyToMany(fetch = FetchType.EAGER)
    private List<MedicalHistory> medicalHistories = new ArrayList<>();

}