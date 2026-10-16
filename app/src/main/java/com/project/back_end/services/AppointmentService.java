
package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    public Appointment updateAppointment(Long appointmentId,
                                         Appointment updatedAppointment) {
        return appointmentRepository.findById(appointmentId)
                .map(existing -> {
                    existing.setPatient(updatedAppointment.getPatient());
                    existing.setDoctor(updatedAppointment.getDoctor());
                    existing.setAppointmentDateTime(
                            updatedAppointment.getAppointmentDateTime());
                    existing.setStatus(updatedAppointment.getStatus());
                    existing.setReason(updatedAppointment.getReason());
                    return appointmentRepository.save(existing);
                })
                .orElse(null);
    }

    public boolean deleteAppointment(Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            return false;
        }

        appointmentRepository.deleteById(appointmentId);
        return true;
    }
}
