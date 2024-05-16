package com.learning.practice.service;

import com.learning.practice.Utils.GenericUpdateService;
import com.learning.practice.model.Doctor;
import com.learning.practice.model.Patient;
import com.learning.practice.repository.DoctorRepository;
import com.learning.practice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@Component
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    GenericUpdateService genericUpdateService;

    @Autowired
    PatientRepository patientRepository;

    public List<Doctor> getdoctorsList() {
        return doctorRepository.findAll();
    }

    public ResponseEntity<Doctor> getDoctorDetails(int doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId);
        if (doctor == null) {
            throw new ResourceNotFoundException("Doctor not found with id: " + doctorId);
        }
        if (!CollectionUtils.isEmpty(doctor.getPatients())) {
            //Filtering Patients by signed Consent
            List<Patient> patientList = doctor.getPatients().stream().filter(signedPatient -> signedPatient.isSignedConsent()).collect(Collectors.toList());
            doctor.setPatients(patientList);
        }
        return ResponseEntity.ok(doctor);
    }

    public Doctor addNewDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public ResponseEntity<HttpStatus> deleteDoctor(int doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId);
        if (doctor == null) {
            throw (new ResourceNotFoundException("Doctor not exist with id: " + doctorId));
        } else {
            doctorRepository.delete(doctor);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Doctor> updateDoctor1(int doctorId, Doctor updateDoctor) throws IllegalAccessException {
        Doctor existingDoctor = doctorRepository.findById(doctorId);
        if (existingDoctor == null) {
            throw (new ResourceNotFoundException("Doctor not found with id: " + doctorId));
        } else {
            existingDoctor = genericUpdateService.updateEntity(existingDoctor, updateDoctor);
            existingDoctor = doctorRepository.save(existingDoctor);
            return new ResponseEntity(existingDoctor, HttpStatus.OK);
        }
    }

    public ResponseEntity<List<Patient>> getPatientListByDocId(int docId) {

        try {
            //Checking doc record available or not
            this.getDoctorDetails(docId);

            //If doc exist pass id to get the patient list under doctor.
            List<Patient> patientList = patientRepository.findAllByDoctorAssigned(docId);
            if (CollectionUtils.isEmpty(patientList)) {
                throw new ResourceNotFoundException("No Patients found under this doctor" + docId);
            }
            return new ResponseEntity(patientList, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }

    }

}
