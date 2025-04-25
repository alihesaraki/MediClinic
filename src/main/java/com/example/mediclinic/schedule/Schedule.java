package com.example.mediclinic.schedule;

import com.example.mediclinic.doctor.Doctor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "appointment-length", nullable = false)
    private int appointmentDurationMin;

    @ManyToOne
    @JoinColumn(name = "doctor", nullable = false)
    private Doctor doctor;
}