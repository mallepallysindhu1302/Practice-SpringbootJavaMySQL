package com.learning.practice.repository;


import com.learning.practice.model.Doctor;
import com.learning.practice.model.Patient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    //Gets All (doctor)CRUD database methods to interact with database.
        Doctor findById(int doctorId);

    @Query(value = "select d.*, p.first_name as patientFirstName from doctor d join patient p on d.doctor_id = p.doctor_assigned " +
            "where p.doctor_assigned =  :docId and p.is_signed_consent = 1"
            , nativeQuery = true )
    Doctor findAllByDoctorAssigned(@Param("docId") int docId);


}
