package com.example.mediclinic.prescription;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("SELECT p FROM prescriptionEntity p JOIN FETCH p.medicalHistories m WHERE m.id = :id")
    List<Prescription> findByMedicalHistory(@Param("id") Long id);

    @Query("SELECT p FROM prescriptionEntity p JOIN FETCH p.medicines m WHERE m.id = :id")
    List<Prescription> findByMedicineId(@Param("id") Long id);

    @Modifying
    @Query("update prescriptionEntity p set p.deleted=true where p.id= :id")
    @Transactional
    void logicalRemove(@Param("id") Long id);

    @Query("SELECT p FROM prescriptionEntity p WHERE p.dateIssued BETWEEN :startDate AND :endDate")
    List<Prescription> findPrescriptionInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
