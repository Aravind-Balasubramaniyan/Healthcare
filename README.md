# Healthcare Appointment System

This is a Spring Boot based RESTful API service for a basic Healthcare Appointment System. 
The service allows booking, rescheduling, canceling appointments, and accessing patient records. 
The application can use a legacy database.

## Features

1. **Book a new appointment**: `POST /api/appointments`
2. **Retrieve all appointments**: `GET /api/appointments`
3. **Reschedule an existing appointment**: `PUT /api/appointments/{appointmentId}`
4. **Cancel an appointment**: `DELETE /api/appointments/{appointmentId}`
5. **Access a patient's records**: `GET /api/patients/{patientId}`

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Git

## Getting Started

### Clone the repository

bash
git clone https://github.com/yourusername/healthcare-appointment-system.git
cd healthcare-appointment-system

Build the project
------------------
Navigate to the project directory and run the following command to build the project:
mvn clean install

Run the application
--------------------
After building the project, you can run the application using the following command:
mvn spring-boot:run

The application will start on http://localhost:8080.

Running Tests
-------------
Unit tests are included in the project to ensure the application functions correctly. 
To run the tests, use the following command
mvn test

API Endpoints
--------------
Book a new appointment
URL: /api/appointments
Method: POST
Request Body:
{
  "patientId": 1,
  "dateTime": "2024-06-01T10:00:00",
  "reason": "Checkup"
}

Retrieve all appointments
---------------------------
URL: /api/appointments
Method: GET

Reschedule an existing appointment
-----------------------------------
URL: /api/appointments/{appointmentId}
Method: PUT
Request Body:
{
  "patientId": 1,
  "dateTime": "2024-06-02T11:00:00",
  "reason": "Follow-up"
}

Cancel an appointment
----------------------
URL: /api/appointments/{appointmentId}
Method: DELETE

Access a patient's records
----------------------------
URL: /api/patients/{patientId}
Method: GET

Exception Handling
--------------------
The application includes custom exception handling for various error scenarios, such as appointment not found, 
patient not found, and general errors.The exceptions return appropriate HTTP status codes and error messages.

Folder Structure
-----------------
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── healthcare
│   │   │           └── appointment
│   │   │               └── Healthcare_Appointment
│   │   │                   ├── controller
│   │   │                   │   ├── AppointmentController.java
│   │   │                   │   └── PatientController.java
│   │   │                   ├── exception
│   │   │                   │   ├── AppointmentNotFoundException.java
│   │   │                   │   ├── AppointmentServiceException.java
│   │   │                   │   └── GlobalExceptionHandler.java
│   │   │                   ├── model
│   │   │                   │   ├── Appointment.java
│   │   │                   │   └── Patient.java
│   │   │                   ├── repository
│   │   │                   │   ├── AppointmentRepository.java
│   │   │                   │   └── PatientRepository.java
│   │   │                   └── service
│   │   │                       ├── AppointmentService.java
│   │   │                       └── PatientService.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── healthcare
│                   └── appointment
│                       └── Healthcare_Appointment
│                           └── controller
│                               └── AppointmentControllerTests.java
│                               └── PatientControllerTests.java
└── README.md

Contact
---------
If you have any questions or issues, please contact [aravindbala1995@gmail.com]
