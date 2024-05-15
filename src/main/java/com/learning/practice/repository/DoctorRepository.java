package com.learning.practice.repository;


import com.learning.practice.model.Doctor;
import com.learning.practice.model.Patient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;


@Component
public interface DoctorRepository extends JpaRepository<Doctor, Integer>
{
    //Gets All (doctor)CRUD database methods to interact with database.
    Doctor findById(int doctorId);

}
