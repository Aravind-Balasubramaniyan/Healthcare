package com.healthcare.appointment.Healthcare_Appointment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.healthcare.appointment.Healthcare_Appointment.model.Appointment;
import com.healthcare.appointment.Healthcare_Appointment.service.AppointmentService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Mock
	private AppointmentService appointmentService;

	@InjectMocks
	private AppointmentController appointmentController;

	@Test
	public void bookAppointmentTest() {// Create an Appointment object
		Appointment appointment = new Appointment();
		appointment.setPatientId(1L);
		appointment.setDateTime(LocalDateTime.now());
		appointment.setReason("Checkup");

		// Mock the service method
		when(appointmentService.bookAppointment(any(Appointment.class))).thenReturn(appointment);

		// Create an HTTP request with the Appointment object
		HttpEntity<Appointment> request = new HttpEntity<>(appointment);

		// Send the request and capture the response
		ResponseEntity<Appointment> response = restTemplate
				.postForEntity("http://localhost:" + port + "/api/appointments", request, Appointment.class);

		// Assert that the response status is 201 Created
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		// Assert that the response body matches the expected appointment
		Appointment responseBody = response.getBody();
		assertThat(responseBody).isNotNull();
		assertThat(responseBody.getPatientId()).isEqualTo(appointment.getPatientId());
		assertThat(responseBody.getDateTime()).isEqualTo(appointment.getDateTime());
		assertThat(responseBody.getReason()).isEqualTo(appointment.getReason());
	}

	@Test
	public void getAllAppointmentsTest() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/appointments",
				String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
    public void rescheduleAppointmentTest() {
        // Create an Appointment object
        Appointment appointment = new Appointment();
        appointment.setPatientId(4L);
        appointment.setDateTime(LocalDateTime.now());
        appointment.setReason("Updated Health Checkup");

        // Mock the service method
        when(appointmentService.rescheduleAppointment(any(Long.class), any(Appointment.class))).thenReturn(appointment);

        // Create an HTTP request with the Appointment object
        HttpEntity<Appointment> request = new HttpEntity<>(appointment);

        // Send the PUT request and capture the response
        ResponseEntity<Appointment> response = restTemplate.exchange("http://localhost:" + port + "/api/appointments/1", HttpMethod.PUT, request, Appointment.class);

        // Assert that the response status is 200 OK
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Assert that the response body matches the expected appointment
        Appointment responseBody = response.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getPatientId()).isEqualTo(appointment.getPatientId());
        assertThat(responseBody.getDateTime()).isEqualTo(appointment.getDateTime());
        assertThat(responseBody.getReason()).isEqualTo(appointment.getReason());
    }

    @Test
    public void cancelAppointmentTest() {
        // Mock the service method
        when(appointmentService.cancelAppointment(any(Long.class))).thenReturn(true);

        // Send the DELETE request and capture the response
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/appointments/2", HttpMethod.DELETE, HttpEntity.EMPTY, String.class);

        // Assert that the response status is 200 OK
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Assert that the response body matches the expected message
        String responseBody = response.getBody();
        assertThat(responseBody).isEqualTo("Deleted appointment with ID: 2");
    }
	
}