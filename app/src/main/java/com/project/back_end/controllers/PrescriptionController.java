
package com.project.back_end.controllers;

import com.project.back_end.models.Prescription;
import com.project.back_end.models.PrescriptionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionController(
            PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Prescription> getPrescriptionById(
            @PathVariable Long id) {
        return prescriptionRepository.findById(id);
    }

    @PostMapping
    public Prescription createPrescription(
            @RequestBody Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }
}
