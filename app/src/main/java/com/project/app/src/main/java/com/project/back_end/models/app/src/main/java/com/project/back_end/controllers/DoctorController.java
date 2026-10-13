
package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final List<Doctor> doctors = new ArrayList<>();

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(
            @RequestBody Doctor doctor) {
        doctors.add(doctor);
        return ResponseEntity.ok(doctor);
    }
}
