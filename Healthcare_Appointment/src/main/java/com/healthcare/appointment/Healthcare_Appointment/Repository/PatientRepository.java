package com.healthcare.appointment.Healthcare_Appointment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.appointment.Healthcare_Appointment.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
