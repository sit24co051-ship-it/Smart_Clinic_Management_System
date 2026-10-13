# Smart Clinic Management System — Database Schema Design

## 1. Overview

The Smart Clinic Management System uses MySQL to store and manage patient details, doctor information, appointment bookings, and prescriptions. The database uses primary keys and foreign keys to maintain data integrity and relationships.

## 2. Database Name

`smart_clinic_db`

## 3. Table Design

### 3.1 Patients

Stores information about patients registered with the clinic.

| Column | Data Type | Constraints | Description |
|---|---|---|---|
| patient_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique patient identifier |
| full_name | VARCHAR(100) | NOT NULL | Patient's full name |
| email | VARCHAR(150) | UNIQUE, NOT NULL | Patient's email |
| phone | VARCHAR(20) | | Contact number |
| date_of_birth | DATE | | Patient's date of birth |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Registration time |

### 3.2 Doctors

Stores doctor profiles and specialization details.

| Column | Data Type | Constraints | Description |
|---|---|---|---|
| doctor_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique doctor identifier |
| full_name | VARCHAR(100) | NOT NULL | Doctor's full name |
| specialization | VARCHAR(100) | NOT NULL | Medical specialization |
| email | VARCHAR(150) | UNIQUE, NOT NULL | Doctor's email |
| phone | VARCHAR(20) | | Contact number |
| consultation_fee | DECIMAL(10,2) | NOT NULL | Consultation fee |

### 3.3 Appointments

Stores appointment bookings between patients and doctors.

| Column | Data Type | Constraints | Description |
|---|---|---|---|
| appointment_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique appointment identifier |
| patient_id | BIGINT | FOREIGN KEY, NOT NULL | References Patients |
| doctor_id | BIGINT | FOREIGN KEY, NOT NULL | References Doctors |
| appointment_datetime | DATETIME | NOT NULL | Scheduled date and time |
| status | VARCHAR(20) | NOT NULL, DEFAULT 'BOOKED' | Booking status |
| reason | VARCHAR(255) | | Reason for consultation |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Booking creation time |

### 3.4 Prescriptions

Stores prescriptions issued by doctors during consultations.

| Column | Data Type | Constraints | Description |
|---|---|---|---|
| prescription_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique prescription identifier |
| appointment_id | BIGINT | FOREIGN KEY, NOT NULL | References Appointments |
| medication_details | TEXT | NOT NULL | Medicine names and instructions |
| notes | TEXT | | Additional medical instructions |
| prescribed_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Prescription creation time |

## 4. Relationships

- One patient can have many appointments.
- One doctor can attend many appointments.
- One appointment can have a prescription record.
- Each appointment references one patient and one doctor.

## 5. Referential Integrity

- `Appointments.patient_id` references `Patients.patient_id`.
- `Appointments.doctor_id` references `Doctors.doctor_id`.
- `Prescriptions.appointment_id` references `Appointments.appointment_id`.
- Primary keys uniquely identify records.
- Foreign keys prevent appointments or prescriptions from referencing nonexistent records.
- The email fields in Patients and Doctors must be unique.

## 6. Indexing

- Create an index on `Appointments.patient_id` to retrieve a patient's appointments efficiently.
- Create an index on `Appointments.doctor_id` to retrieve a doctor's appointments efficiently.
- Create an index on `Appointments.appointment_datetime` to support date-based appointment searches.

## 7. Conclusion

This relational schema provides a structured foundation for clinic operations. It supports patient registration, doctor management, appointment scheduling, and prescription storage while maintaining data consistency through keys and constraints.
