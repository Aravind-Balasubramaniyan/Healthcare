package com.healthcare.appointment.Healthcare_Appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.appointment.Healthcare_Appointment.Repository.PatientRepository;
import com.healthcare.appointment.Healthcare_Appointment.exception.AppointmentNotFoundException;
import com.healthcare.appointment.Healthcare_Appointment.model.Patient;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

	public Patient getPatientById(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Patient not found with id: " + id));
	}
}