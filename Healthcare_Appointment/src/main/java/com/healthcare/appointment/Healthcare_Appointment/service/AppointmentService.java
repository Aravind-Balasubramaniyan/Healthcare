package com.healthcare.appointment.Healthcare_Appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthcare.appointment.Healthcare_Appointment.Repository.AppointmentRepository;
import com.healthcare.appointment.Healthcare_Appointment.exception.AppointmentNotFoundException;
import com.healthcare.appointment.Healthcare_Appointment.exception.AppointmentServiceException;
import com.healthcare.appointment.Healthcare_Appointment.model.Appointment;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Transactional
	public Appointment bookAppointment(Appointment appointment) {
		try {
			return appointmentRepository.save(appointment);
		} catch (Exception e) {
			throw new AppointmentServiceException("Error booking appointment: " + e.getMessage());
		}
	}

	public List<Appointment> getAppointmentById(Long id) {
		try {
			return appointmentRepository.findAll();
		} catch (Exception e) {
			throw new AppointmentServiceException("Error retrieving appointments: " + e.getMessage());
		}
	}

	@Transactional
	public Appointment rescheduleAppointment(Long id, Appointment newAppointment) {
		return appointmentRepository.findById(id).map(existingAppointment -> {
			existingAppointment.setPatientId(newAppointment.getPatientId());
			existingAppointment.setDateTime(newAppointment.getDateTime());
			existingAppointment.setReason(newAppointment.getReason());
			return appointmentRepository.save(existingAppointment);
		}).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));
	}

	@Transactional
	public boolean cancelAppointment(Long appointmentId) {
		if (appointmentRepository.existsById(appointmentId)) {
			appointmentRepository.deleteById(appointmentId);
			return true;
		} else {
			throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found");
		}
	}

	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}
}