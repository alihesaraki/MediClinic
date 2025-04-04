package com.example.mediclinic.repository;

import com.example.mediclinic.model.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from patientEntity p where p.lastName =:lastName")
    List<Patient> findByLastName(@Param("lastName") String lastName);

//    @Query("select p from patientEntity p cross join appointmentEntity a where a.id=:id")
    @Query("SELECT p FROM patientEntity p JOIN FETCH p.appointments a WHERE a.id = :id")
    List<Patient> findByAppointment(@Param("id") Long id);

    @Modifying
    @Query("update patientEntity p set p.deleted=true where p.patientId= :patientId")
    @Transactional
    void logicalRemove(@Param("patientId") Long patientId);

}
