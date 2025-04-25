package com.example.mediclinic.appointment;

import com.example.mediclinic.schedule.Schedule;
import com.example.mediclinic.patient.Patient;
import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Entity(name = "appointmentEntity")
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

}
