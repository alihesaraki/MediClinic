package com.example.mediclinic.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // قلب، مغز و اعصاب، ارتوپدی

    @OneToMany(mappedBy = "specialization")
    private Set<Doctor> doctors;
}
