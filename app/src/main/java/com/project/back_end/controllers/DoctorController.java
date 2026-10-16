
package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final List<Doctor> doctors = new ArrayList<>();

    @GetMapping
    public List<Doctor> getDoctors(
            @RequestParam(required = false) String specialty) {

        if (specialty == null || specialty.isBlank()) {
            return doctors;
        }

        return doctors.stream()
                .filter(doctor -> doctor.getSpecialization()
                        .equalsIgnoreCase(specialty))
                .toList();
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        doctors.add(doctor);
        return doctor;
    }
}
