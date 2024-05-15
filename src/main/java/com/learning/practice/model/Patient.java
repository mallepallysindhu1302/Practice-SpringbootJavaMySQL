package com.learning.practice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "doctor_assigned")
    private int doctorAssigned;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="doctor_assigned")
//    private Doctor doctorAssigned;

    @Column(name="is_signedConsent", columnDefinition="tinyint(1)")
    private boolean isSignedConsent;

}

