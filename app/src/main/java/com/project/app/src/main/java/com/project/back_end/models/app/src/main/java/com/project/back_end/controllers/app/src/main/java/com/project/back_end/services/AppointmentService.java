
package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private final List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public Appointment createAppointment(Appointment appointment) {
        appointments.add(appointment);
        return appointment;
    }

    public Appointment getAppointmentById(Long appointmentId) {
        return appointments.stream()
                .filter(a -> a.getAppointmentId() != null
                        && a.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElse(null);
    }
}
