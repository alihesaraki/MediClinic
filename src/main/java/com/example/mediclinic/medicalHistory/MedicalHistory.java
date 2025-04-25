package com.example.mediclinic.medicalHistory;

import com.example.mediclinic.appointment.Appointment;
import com.example.mediclinic.prescription.Prescription;
import com.example.mediclinic.patient.Patient;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @Column(name = "treatment", nullable = false)
    private String treatment;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "medicalHistory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "prescriptions", nullable = false)
    private List<Prescription> prescription;

    @ManyToOne
    private Appointment appointment;

//    todo : لیست نسخه ها ... لیست آزمایش ... لیست گزارش
//    todo : تصویر ... سوابق اسکن شده mongo db
}