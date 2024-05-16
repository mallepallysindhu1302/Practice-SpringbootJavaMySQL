package com.learning.practice.controller;


import com.learning.practice.model.Doctor;
import com.learning.practice.model.Patient;
import com.learning.practice.service.DoctorService;
import com.learning.practice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //
@RequestMapping("/Doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    //Get All Doctors
    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllDoctorList() {
        return doctorService.getdoctorsList();
    }

    /*Get Doctor details by ID
       @pararm doctorId
       return Doctor */
    @GetMapping("getDoctor/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int doctorId) {
        try {
            return doctorService.getDoctorDetails(doctorId);
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /* Add Doctor
       @RequestBody Doctor details
        return Doctor */
    @PostMapping("/createDoctor")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addNewDoctor(doctor);
    }

    /* Delete Doctor using docId
       @param docId - Doctor Id
       return Status Ok */
    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<HttpStatus> deleteDoctorID(@PathVariable int doctorId) {
        try {
            return doctorService.deleteDoctor(doctorId);
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /* Update Doctor using docId
        @param docId - Doctor Id
        @RequestBody Doctor Details
        return updated Doctor */
    @PutMapping("/update/{doctorId}")
    public ResponseEntity<Doctor> updatedoctor(@PathVariable int doctorId, @RequestBody Doctor doctor) throws IllegalAccessException {
        try {
            return doctorService.updateDoctor1(doctorId, doctor);
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /* Get all the patients by docId who Signed Consent
       @param docId - Doctor Id
       return patients list*/
    @GetMapping("/getPatientsByDocId/{docId}")
    public ResponseEntity<List<Patient>> getPatientListByDocId(@PathVariable int docId) {
        try {
            List<Patient> patientListForDoc = doctorService.getPatientListByDocId(docId).getBody();
            return new ResponseEntity<>(patientListForDoc, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}