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

    //Endpoint to get All Doctors
    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllDoctorList() {
        return doctorService.getdoctorsList();}

    //Endpoint to get Doctor by ID
    @GetMapping("getDoctor/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable  int doctorId){
        try{
        return doctorService.getDoctorDetails(doctorId); }
    catch(Exception exception)
    {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    //Endpoint to Add New Doctor
    @PostMapping("/createDoctor")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addNewDoctor(doctor);   }

    //Endpoint to delete Doctor
    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity <HttpStatus> deleteDoctorID(@PathVariable int doctorId)  {
        try{
            return doctorService.deleteDoctor(doctorId);
        } catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Endpoint to update Patient
    @PutMapping("/update/{doctorId}")
    public ResponseEntity<Doctor> updatedoctor(@PathVariable int doctorId, @RequestBody Doctor doctor)throws IllegalAccessException {
        try{
            return doctorService.updateDoctor1(doctorId,doctor);
        }
        catch(Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
            }

    @GetMapping("/getPatientsByDocId/{docId}")
    public ResponseEntity<List<Patient>> getPatientListByDocId(@PathVariable int docId) {
        try {
            List<Patient> patientListForDoc= doctorService.getPatientListByDocId(docId).getBody();
            return new ResponseEntity<>(patientListForDoc, HttpStatus.OK);
        }
        catch(Exception exception)
        {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}