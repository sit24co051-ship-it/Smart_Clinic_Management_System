```java
package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.models.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Transactional(readOnly = true)
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Doctor> getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }

    @Transactional(readOnly = true)
    public List<Doctor> searchDoctorsByName(String name) {
        return doctorRepository.findByFullNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationIgnoreCase(specialization);
    }

    @Transactional(readOnly = true)
    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorRepository.findByEmailIgnoreCase(email);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> updateDoctor(
            Long doctorId, Doctor updatedDoctor) {

        return doctorRepository.findById(doctorId)
                .map(existing -> {
                    existing.setFullName(updatedDoctor.getFullName());
                    existing.setSpecialization(updatedDoctor.getSpecialization());
                    existing.setEmail(updatedDoctor.getEmail());
                    existing.setPhone(updatedDoctor.getPhone());
                    existing.setConsultationFee(
                            updatedDoctor.getConsultationFee());
                    return doctorRepository.save(existing);
                });
    }

    public boolean deleteDoctor(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            return false;
        }

        doctorRepository.deleteById(doctorId);
        return true;
    }
}
```
