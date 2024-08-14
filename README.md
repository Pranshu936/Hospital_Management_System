# Hospital Management System

## Overview

The Hospital Management System is a comprehensive Java-based application designed to manage and streamline various aspects of hospital operations using JDBC (Java Database Connectivity). This system facilitates efficient handling of patient records, doctor details, appointment scheduling, medical documentation, and billing processes. The application is built to improve the efficiency and accuracy of hospital management tasks.

## Features

- **Patient Management**: 
  - **Add** new patients with personal and admission details.
  - **Update** patient information as required.
  - **Delete** patient records.
  - **View** patient details and history.

- **Doctor Management**:
  - **Add** new doctors with their specialization and contact information.
  - **Update** doctor details as needed.
  - **Delete** doctor records.
  - **View** doctor profiles and specialties.

- **Appointment Scheduling**:
  - **Schedule** appointments for patients with doctors.
  - **Update** appointment details including date and status.
  - **Cancel** appointments when necessary.
  - **View** all scheduled appointments and their statuses.

- **Medical Records Management**:
  - **Record** patient diagnoses, treatments, and prescriptions.
  - **Update** medical records with new information as treatments progress.
  - **View** comprehensive medical histories for patients.

- **Billing System**:
  - **Generate** billing records for patient services.
  - **Update** billing information including amounts and payment statuses.
  - **View** all billing details and payment histories.

## Database Design

The database is structured into the following tables to effectively manage hospital data:

### Patients Table

- **patient_id** (Primary Key): Unique identifier for each patient.
- **name**: Patient's full name.
- **age**: Patient's age.
- **gender**: Patient's gender.
- **address**: Patient's residential address.
- **phone_number**: Contact number of the patient.
- **admission_date**: Date when the patient was admitted.
- **doctor_id** (Foreign Key): References the doctor assigned to the patient.

### Doctors Table

- **doctor_id** (Primary Key): Unique identifier for each doctor.
- **name**: Doctor's full name.
- **specialization**: Field of expertise or specialization of the doctor.
- **phone_number**: Contact number of the doctor.
- **email**: Email address of the doctor.

### Appointments Table

- **appointment_id** (Primary Key): Unique identifier for each appointment.
- **patient_id** (Foreign Key): References the patient for the appointment.
- **doctor_id** (Foreign Key): References the doctor for the appointment.
- **appointment_date**: Date and time of the appointment.
- **status**: Current status of the appointment (e.g., scheduled, completed, canceled).

### Medical Records Table

- **record_id** (Primary Key): Unique identifier for each medical record.
- **patient_id** (Foreign Key): References the patient related to the record.
- **diagnosis**: Details of the patient's diagnosis.
- **treatment**: Details of the treatment provided.
- **prescription**: Medications prescribed to the patient.
- **record_date**: Date when the medical record was created.

### Billing Table

- **bill_id** (Primary Key): Unique identifier for each billing record.
- **patient_id** (Foreign Key): References the patient for whom the bill was generated.
- **total_amount**: Total amount billed.
- **paid_amount**: Amount paid by the patient.
- **payment_date**: Date when the payment was made.

## Setup and Installation

1. **Database Setup**:
   - Create a database instance and set up tables according to the provided schema.
   - Configure the database connection details in the application.

2. **Project Setup**:
   - Clone the repository: `git clone <repository-url>`
   - Navigate to the project directory: `cd <project-directory>`
   - Ensure you have the JDBC driver for your database (e.g., MySQL Connector/J, PostgreSQL JDBC Driver).
   - Compile the Java source files: `javac -cp .;path/to/jdbc/driver/*.jar *.java`
   - Run the application: `java -cp .;path/to/jdbc/driver/*.jar MainClass`

3. **Configuration**:
   - Update the database connection properties in the `db.properties` file or within the code as necessary.
   - Ensure all required libraries and drivers are included in your project classpath.

## Usage

- **Patient Management**:
  - Access the patient management interface to add, update, delete, or view patient records.

- **Doctor Management**:
  - Use the doctor management interface to handle doctor records and details.

- **Appointments**:
  - Manage appointments through the scheduling interface, including creating, updating, and canceling appointments.

- **Medical Records**:
  - Add and update medical records, and view patient histories.

- **Billing**:
  - Handle billing processes including generating bills, updating payment statuses, and viewing billing records.

## Dependencies

- Java Development Kit (JDK)
- JDBC Driver for your specific database (e.g., MySQL, PostgreSQL)
- Additional libraries for database interaction (if any)

## Contributing

Contributions are welcome! If you have suggestions, improvements, or bug fixes, please fork the repository and submit a pull request with your changes. Ensure that you follow best practices for code quality and documentation.

