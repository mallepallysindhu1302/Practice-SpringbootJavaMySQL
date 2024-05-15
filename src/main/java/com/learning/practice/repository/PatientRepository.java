package com.learning.practice.repository;

import com.learning.practice.model.Doctor;
import com.learning.practice.model.Patient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;

//Extends
@Component
public interface PatientRepository extends JpaRepository<Patient, Integer>
{

    Patient findPatientByPatientId(int patientId);
    List<Patient> findAllByDoctorAssigned(int docId);

}
