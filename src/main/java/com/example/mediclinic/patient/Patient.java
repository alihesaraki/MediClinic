package com.example.mediclinic.patient;

import com.example.mediclinic.appointment.Appointment;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Cacheable
@Entity(name = "patientEntity")
@Table(name = "patients")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "patientId")
@SQLRestriction("deleted = false")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "phone")
    private String phone;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();
}
