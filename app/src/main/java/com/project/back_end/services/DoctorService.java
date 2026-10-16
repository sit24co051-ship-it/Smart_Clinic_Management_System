
package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.models.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long doctorId, Doctor updatedDoctor) {
        return doctorRepository.findById(doctorId)
                .map(existing -> {
                    existing.setFullName(updatedDoctor.getFullName());
                    existing.setSpecialization(updatedDoctor.getSpecialization());
                    existing.setEmail(updatedDoctor.getEmail());
                    existing.setPhone(updatedDoctor.getPhone());
                    existing.setConsultationFee(
                            updatedDoctor.getConsultationFee());
                    return doctorRepository.save(existing);
                })
                .orElse(null);
    }

    public boolean deleteDoctor(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            return false;
        }

        doctorRepository.deleteById(doctorId);
        return true;
    }
}
