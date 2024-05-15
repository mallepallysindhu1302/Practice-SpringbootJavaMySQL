package com.learning.practice.controller;

import com.learning.practice.model.Patient;
import com.learning.practice.service.PatientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    //Endpoint to get All Patients
    @GetMapping("/getAllPatients")
    public List<Patient> getPatientList() {
        return patientService.getpatientsList();
    }

    //Endpoint to get Patient by ID
    @GetMapping("getPatient/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable  int patientId){
        try{
        return patientService.getPatientDetails(patientId);    }
        catch(Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Endpoint to Add New Patient
    @PostMapping("/createPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
      try{
        return patientService.addNewPatient(patient);
      } catch (Exception exception){
          return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Endpoint to delete Patient
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<HttpStatus> deletePatientID(@PathVariable int patientId) {
       try{
           return patientService.deletePatient(patientId);
       } catch (Exception exception){
         return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }

    //Endpoint to update Patient
    @PutMapping("/update/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int patientId, @RequestBody Patient patient)  {
        try{
            return patientService.updatePatient1(patientId, patient);
        } catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{patientId}/doctor_assign/{doctorId}")
    public ResponseEntity<Patient> assignDoctorToPatient(@PathVariable int doctorId, @PathVariable int patientId){
        try{
            return ResponseEntity.ok(patientService.addDoctor(doctorId,patientId).getBody());
        } catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
