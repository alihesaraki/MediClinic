package com.example.mediclinic.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime availableDateTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
