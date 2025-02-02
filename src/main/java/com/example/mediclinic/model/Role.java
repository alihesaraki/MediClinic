package com.example.mediclinic.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // admin, doctor, patient

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
