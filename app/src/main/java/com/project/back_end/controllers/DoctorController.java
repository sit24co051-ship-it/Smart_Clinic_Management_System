
package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.models.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public List<Doctor> getDoctors(
            @RequestParam(required = false) String specialty) {

        List<Doctor> doctors = doctorRepository.findAll();

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
        return doctorRepository.save(doctor);
    }
}
