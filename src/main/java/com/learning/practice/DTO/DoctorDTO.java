//package com.learning.practice.DTO;
//
//import com.learning.practice.model.Patient;
//import jakarta.persistence.*;
//import lombok.Data;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Data
//@Entity
//@Component
//public class DoctorDTO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int doctor_id;
//
//    private String first_name;
//
//    private String last_name;
//
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
//    private List<PatientDTO> patients;
//}
