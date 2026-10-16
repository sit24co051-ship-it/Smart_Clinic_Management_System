
package com.project.back_end.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatient_PatientId(Long patientId);

    List<Appointment> findByDoctor_DoctorId(Long doctorId);

    List<Appointment> findByDoctor_DoctorIdAndAppointmentDateTimeBetween(
            Long doctorId,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Appointment> findByPatient_PatientIdAndAppointmentDateTimeBetween(
            Long patientId,
            LocalDateTime start,
            LocalDateTime end
    );
}
