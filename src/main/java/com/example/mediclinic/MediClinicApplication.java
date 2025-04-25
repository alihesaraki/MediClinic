package com.example.mediclinic;

import com.example.mediclinic.appointment.Appointment;
import com.example.mediclinic.patient.Patient;
import com.example.mediclinic.appointment.AppointmentService;
import com.example.mediclinic.patient.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class MediClinicApplication {

    private final AppointmentService appointmentService;
    private final PatientService patientService;

    public MediClinicApplication(AppointmentService appointmentService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MediClinicApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // Creating a test patient
            Patient patient = new Patient();
            patient.setFirstName("Ali");
            patient.setLastName("Hesaraki");
            patient.setAge(29);
            patient.setPhone("09121234567");
            Patient savedPatient = patientService.createPatient(patient);
            System.out.println("Test patient created: " + savedPatient);

            // Creating and saving appointments for the patient
            Appointment appointment1 = new Appointment();
            appointment1.setStartDateTime(LocalDateTime.of(2000, 1, 1, 9, 0));
            appointment1.setEndDateTime(LocalDateTime.of(2000, 1, 1, 12, 0));
            appointment1.setPatient(savedPatient);
//            appointmentService.createAppointment(appointment1);  // Use createAppointment instead of save
            System.out.println("Appointment 1 Saved: " + appointment1);

            Appointment appointment2 = new Appointment();
            appointment2.setStartDateTime(LocalDateTime.of(2000, 1, 1, 7, 0));
            appointment2.setEndDateTime(LocalDateTime.of(2000, 1, 1, 10, 0));
            appointment2.setPatient(savedPatient);
//            appointmentService.createAppointment(appointment2);  // Use createAppointment instead of save
            System.out.println("Appointment 2 Saved: " + appointment2);
        };
    }

}
