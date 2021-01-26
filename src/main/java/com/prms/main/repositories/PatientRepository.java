package com.prms.main.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prms.main.models.Patient;

import java.util.List;
@Repository("patientRepository")
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findById(long id);
}