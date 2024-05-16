package com.learning.practice.service;

import com.learning.practice.Utils.GenericUpdateService;
import com.learning.practice.model.Doctor;
import com.learning.practice.model.Patient;
import com.learning.practice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    GenericUpdateService genericUpdateService;

    @Autowired
    DoctorService doctorService;

    public List<Patient> getpatientsList() {
        return patientRepository.findAll();
    }

    public ResponseEntity<Patient> getPatientDetails(int patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
        }
        return ResponseEntity.ok(patient);
    }


    public ResponseEntity<Patient> addNewPatient(Patient patient) {
        if (patient.getDoctorAssigned() == 0) {
            throw new ResourceNotFoundException("Please Choose your doctor");
        } else {
            Patient resPatient = patientRepository.save(patient);
            return ResponseEntity.ok(resPatient);
        }
    }

    public ResponseEntity<List<Patient>> addNewPatients(List<Patient> patientList) {
        patientList.forEach(patient -> {
            if (patient.getDoctorAssigned() == 0) {
                throw new ResourceNotFoundException("Please Choose your doctor for " + patient.getFirstName());
            }
        });
        List<Patient> resPatient = patientRepository.saveAll(patientList);
        return ResponseEntity.ok(resPatient);
    }

    public ResponseEntity<HttpStatus> deletePatient(int patientId) {
        Patient patient = patientRepository.findPatientByPatientId(patientId);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not exist with id: " + patientId);
        } else {
            patientRepository.delete(patient);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Patient> updatePatient1(int patientId, Patient updatePatient) throws IllegalAccessException {
        Patient existingPatient = patientRepository.findPatientByPatientId(patientId);
        if (existingPatient == null) {
            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
        } else {
            existingPatient = genericUpdateService.updateEntity(existingPatient, updatePatient);
            Patient resPatient = patientRepository.save(existingPatient);
            return ResponseEntity.ok(resPatient);
        }
    }

//    public ResponseEntity<Patient> addDoctor(int doctorId, int patientId) {
//        try {
//            Patient patient = this.getPatientDetails(patientId).getBody();
//            Doctor doctor = doctorService.getDoctorDetails(doctorId).getBody();
//            patient.setDoctorAssigned(doctorId);
//            Patient resPatient = patientRepository.save(patient);
//            return ResponseEntity.ok(resPatient);
//        } catch (Exception exception) {
//            throw exception;
//        }
//
//    }

}