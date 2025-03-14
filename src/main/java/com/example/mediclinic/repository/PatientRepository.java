package com.example.mediclinic.repository;

import com.example.mediclinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select p from patientEntity p where p.lastName =:lastName")
    List<Patient>findByLastName(@Param("lastName") String lastName);
}
