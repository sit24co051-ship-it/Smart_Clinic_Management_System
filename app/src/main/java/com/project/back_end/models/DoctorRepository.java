
package com.project.back_end.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByFullNameContainingIgnoreCase(String fullName);

    List<Doctor> findBySpecializationIgnoreCase(String specialization);

    Optional<Doctor> findByEmailIgnoreCase(String email);
}
