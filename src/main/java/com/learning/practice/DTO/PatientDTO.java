//package com.learning.practice.DTO;
//
//import com.learning.practice.model.Doctor;
//import jakarta.persistence.*;
//import lombok.Data;
//import org.springframework.stereotype.Component;
//
//@Data
//@Entity
//@Component
//public class PatientDTO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int patient_id;
//
//    private String first_name;
//
//    private String last_name;
//
//    @ManyToOne
//    @JoinColumn(name="doctor_assigned")
//    private DoctorDTO doctor;
//}
