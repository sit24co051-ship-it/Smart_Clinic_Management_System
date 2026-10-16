package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Transactional(readOnly = true)
    public Optional<Appointment> getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatient_PatientId(patientId);
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctor_DoctorId(doctorId);
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAppointmentsByDoctorAndDate(
            Long doctorId, LocalDate date) {
        return appointmentRepository
                .findByDoctor_DoctorIdAndAppointmentDateTimeBetween(
                        doctorId, date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay().minusNanos(1));
    }

    public Optional<Appointment> updateAppointment(
            Long appointmentId, Appointment updatedAppointment) {
        return appointmentRepository.findById(appointmentId)
                .map(existing -> {
                    existing.setPatient(updatedAppointment.getPatient());
                    existing.setDoctor(updatedAppointment.getDoctor());
                    existing.setAppointmentDateTime(
                            updatedAppointment.getAppointmentDateTime());
                    existing.setStatus(updatedAppointment.getStatus());
                    existing.setReason(updatedAppointment.getReason());
                    return appointmentRepository.save(existing);
                });
    }

    public boolean deleteAppointment(Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            return false;
        }

        appointmentRepository.deleteById(appointmentId);
        return true;
    }
}
