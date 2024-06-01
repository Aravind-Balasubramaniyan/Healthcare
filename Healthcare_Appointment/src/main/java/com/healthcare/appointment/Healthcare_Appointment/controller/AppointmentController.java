package com.healthcare.appointment.Healthcare_Appointment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.appointment.Healthcare_Appointment.exception.AppointmentNotFoundException;
import com.healthcare.appointment.Healthcare_Appointment.exception.AppointmentServiceException;
import com.healthcare.appointment.Healthcare_Appointment.model.Appointment;
import com.healthcare.appointment.Healthcare_Appointment.service.AppointmentService;

@RestController
@RequestMapping("/api")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/appointments")
	public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
		Appointment createdAppointment = appointmentService.bookAppointment(appointment);
		return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
	}

	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		List<Appointment> appointments = appointmentService.getAllAppointments();
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}

	@PutMapping("/appointments/{appointmentId}")
	public ResponseEntity<Appointment> rescheduleAppointment(@PathVariable Long appointmentId,
			@RequestBody Appointment appointment) {
		Appointment updatedAppointment = appointmentService.rescheduleAppointment(appointmentId, appointment);
		return updatedAppointment != null ? new ResponseEntity<>(updatedAppointment, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/appointments/{appointmentId}")
	public ResponseEntity<?> cancelAppointment(@PathVariable Long appointmentId) {
		try {
			boolean isDeleted = appointmentService.cancelAppointment(appointmentId);
			if (isDeleted) {
				return ResponseEntity.ok("Deleted appointment with ID: " + appointmentId);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (AppointmentNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new AppointmentServiceException("An error occurred while deleting the appointment");
		}
	}
}
