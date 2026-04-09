package com.pathwise.backend.repository;

import com.pathwise.backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByStudentEmail(String studentEmail);
    List<Appointment> findByMentorName(String mentorName);
}