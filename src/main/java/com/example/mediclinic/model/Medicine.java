package com.example.mediclinic.model;

import lombok.*;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "medicines")
    private Set<Prescription> prescriptions;
}
