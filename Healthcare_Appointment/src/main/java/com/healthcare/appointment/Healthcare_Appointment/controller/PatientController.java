package com.healthcare.appointment.Healthcare_Appointment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.appointment.Healthcare_Appointment.model.Patient;
import com.healthcare.appointment.Healthcare_Appointment.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping("/patients/{patientId}")
	public ResponseEntity<Patient> getPatient(@PathVariable Long patientId) {
		Patient patient = patientService.getPatientById(patientId);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
}
