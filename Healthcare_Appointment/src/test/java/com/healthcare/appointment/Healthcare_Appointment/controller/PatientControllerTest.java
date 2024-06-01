package com.healthcare.appointment.Healthcare_Appointment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.healthcare.appointment.Healthcare_Appointment.model.Patient;
import com.healthcare.appointment.Healthcare_Appointment.service.PatientService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private PatientService patientService;

	@Test
	public void getPatientTest() {
		// Create a Patient object
		Patient patient = new Patient();
		patient.setId(1L);
		patient.setName("John Doe");

		// Mock the service method
		when(patientService.getPatientById(any(Long.class))).thenReturn(patient);

		// Send a GET request and capture the response
		ResponseEntity<Patient> response = restTemplate.getForEntity("http://localhost:" + port + "/api/patients/1",
				Patient.class);

		// Assert that the response status is 200 OK
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		// Assert that the response body matches the expected patient
		Patient responseBody = response.getBody();
		assertThat(responseBody).isNotNull();
		assertThat(responseBody.getId()).isEqualTo(patient.getId());
		assertThat(responseBody.getName()).isEqualTo(patient.getName());
	}
}
