package com.example.mediclinic.service;

import com.example.mediclinic.model.Appointment;
import com.example.mediclinic.model.Doctor;
import com.example.mediclinic.model.Patient;
import com.example.mediclinic.model.Schedule;
import com.example.mediclinic.repository.AppointmentRepository;
import com.example.mediclinic.repository.DoctorRepository;
import com.example.mediclinic.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, ScheduleRepository scheduleRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<LocalDateTime> findAvailableTime(String doctorCode, LocalDate date) {
        Optional<Schedule> scheduleOpt = findScheduleByDoctorCodeAndDate(doctorCode, date);
        if (scheduleOpt.isEmpty()) {
            return Collections.emptyList();
        }

        Schedule schedule = scheduleOpt.get();

        LocalDateTime dayStart = schedule.getStartDateTime();
        LocalDateTime dayEnd = schedule.getEndDateTime();
        int appointmentDurationMin = schedule.getAppointmentDurationMin();

        List<LocalDateTime> availableTimes = new ArrayList<>();
        LocalDateTime currentTime = dayStart;
        while (currentTime.isBefore(dayEnd)){
            LocalDateTime endTime = currentTime.plusMinutes(appointmentDurationMin);
            if (endTime.isAfter(dayEnd)){
                break;
            }

            List<Appointment> overlapping = appointmentRepository.findOverlappingAppointments(schedule.getId(), currentTime, endTime);
            if (overlapping.isEmpty()){
                availableTimes.add(currentTime);
            }
            currentTime = currentTime.plusMinutes(appointmentDurationMin);
        }
        return availableTimes;
    }


    @Transactional
    public Appointment saveAppointment(String doctorCode, LocalDateTime chosenStartTime, Patient patient) throws Exception{
        Optional<Schedule> scheduleOpt = findScheduleByDoctorCodeAndDate(
                doctorCode, chosenStartTime.toLocalDate()
        );
        if (scheduleOpt.isEmpty()){
            throw new Exception("No schedule found for this doctor");
        }
        Schedule schedule = scheduleOpt.get();

        int appointmentDurationMin= schedule.getAppointmentDurationMin();
        LocalDateTime chosenEndTime = chosenStartTime.plusMinutes(appointmentDurationMin);

        if (chosenStartTime.isBefore(schedule.getStartDateTime()) || chosenEndTime.isAfter(schedule.getEndDateTime())){
            throw new Exception("Chosen time is not on the doctor's schedule");
        }

        if (appointmentRepository.existsByScheduleIdAndStartTime(schedule.getId(), chosenStartTime )){
            throw new Exception("This appointment is already booked");
        }

        Appointment appointment = Appointment.builder()
                .schedule(schedule)
                .patient(patient)
                .startDateTime(chosenStartTime)
                .endDateTime(chosenEndTime)
                .build();
        return appointmentRepository.save(appointment);
    }

    private Optional<Schedule> findScheduleByDoctorCodeAndDate (String doctorCode, LocalDate date) {
        Optional <Doctor> doctorOpt = doctorRepository.findByDoctorCode(doctorCode);
        if (doctorOpt.isEmpty()) {
            return Optional.empty();
        }
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return scheduleRepository.findByDoctorIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(
                doctorOpt.get().getId(), endOfDay, startOfDay
        );
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }


//    public Appointment updateAppointment(Long id, String doctorCode, Appointment appointment ) {
//        return appointmentRepository.findById(id)
//                .map(existingAppointment -> {
//                    existingAppointment.setStartDateTime(updatedAppointment.getStartDateTime());
//                    existingAppointment.setEndDateTime(updatedAppointment.getEndDateTime());
//                    existingAppointment.setPatient(updatedAppointment.getPatient());
//                    return appointmentRepository.save(existingAppointment);
//                })
//                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
//    }

    // Delete an appointment by ID
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Get appointments by patient ID
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientPatientId(patientId);
    }

}
