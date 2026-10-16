package com.project.back_end.controllers;

import com.project.back_end.models.Patient;
import com.project.back_end.models.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(
            @PathVariable Long id) {
        return patientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatients(
            @RequestParam String name) {
        return ResponseEntity.ok(
                patientRepository.findByFullNameContainingIgnoreCase(name));
    }

    @GetMapping("/email")
    public ResponseEntity<Patient> getPatientByEmail(
            @RequestParam String email) {
        return patientRepository.findByEmailIgnoreCase(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(
            @Valid @RequestBody Patient patient) {
        if (patientRepository.existsByEmailIgnoreCase(patient.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Patient saved = patientRepository.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody Patient patient) {
        return patientRepository.findById(id).map(existing -> {
            existing.setFullName(patient.getFullName());
            existing.setEmail(patient.getEmail());
            existing.setPhone(patient.getPhone());
            existing.setDateOfBirth(patient.getDateOfBirth());

            return ResponseEntity.ok(patientRepository.save(existing));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (!patientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
