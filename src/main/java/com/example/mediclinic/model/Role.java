package com.example.mediclinic.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // admin, doctor, patient

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
