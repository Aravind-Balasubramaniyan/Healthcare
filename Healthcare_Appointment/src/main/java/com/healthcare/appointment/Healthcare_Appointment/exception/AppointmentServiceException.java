package com.healthcare.appointment.Healthcare_Appointment.exception;

public class AppointmentServiceException extends RuntimeException {
    public AppointmentServiceException(String message) {
        super(message);
    }
}
