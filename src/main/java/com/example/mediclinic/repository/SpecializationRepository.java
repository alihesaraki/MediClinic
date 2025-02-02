package com.example.mediclinic.repository;

import com.example.mediclinic.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Specialization findByName(String name);
}
