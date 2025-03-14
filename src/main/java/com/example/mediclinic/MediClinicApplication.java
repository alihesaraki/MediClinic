package com.example.mediclinic;

import com.example.mediclinic.model.Appointment;
import com.example.mediclinic.service.AppointmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class MediClinicApplication {
    private final AppointmentService appointmentService;

    public MediClinicApplication(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    public static void main(String[] args) {
        SpringApplication.run(MediClinicApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            Appointment appointment1 = new Appointment();
            appointment1.setStartDateTime(LocalDateTime.of(2000, 1, 1, 9, 0, 0));
            appointment1.setEndDateTime(LocalDateTime.of(2000, 1, 1, 12, 0, 0));
            appointmentService.save(appointment1);
            System.out.println(appointment1);
            System.out.println("1 Saved");

            Appointment appointment2 = new Appointment();
            appointment2.setStartDateTime(LocalDateTime.of(2000, 1, 1, 7, 0, 0));
            appointment2.setEndDateTime(LocalDateTime.of(2000, 1, 1, 10, 0, 0));
            appointmentService.save(appointment2);
            System.out.println(appointment2);
            System.out.println("2 Saved");
        };
}
