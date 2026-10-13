
package com.project.back_end.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final List<Prescription> prescriptions = new ArrayList<>();

    public static class Prescription {
        private Long prescriptionId;
        private Long appointmentId;
        private String medicationDetails;
        private String notes;

        public Prescription() {
        }

        public Prescription(Long prescriptionId, Long appointmentId,
                            String medicationDetails, String notes) {
            this.prescriptionId = prescriptionId;
            this.appointmentId = appointmentId;
            this.medicationDetails = medicationDetails;
            this.notes = notes;
        }

        public Long getPrescriptionId() {
            return prescriptionId;
        }

        public void setPrescriptionId(Long prescriptionId) {
            this.prescriptionId = prescriptionId;
        }

        public Long getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(Long appointmentId) {
            this.appointmentId = appointmentId;
        }

        public String getMedicationDetails() {
            return medicationDetails;
        }

        public void setMedicationDetails(String medicationDetails) {
            this.medicationDetails = medicationDetails;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }

    @PostMapping
    public ResponseEntity<Prescription> addPrescription(
            @RequestBody Prescription prescription) {
        prescriptions.add(prescription);
        return ResponseEntity.ok(prescription);
    }
}
