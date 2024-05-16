package com.learning.practice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@Getter // Access Instance variables out of the class
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //To make the class JPA Entity
@Table(name="doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor_id")
    private int doctorId;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable=false)
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_assigned", referencedColumnName = "doctor_id")
    private List<Patient> patients = new ArrayList<>();

}
