package com.floodreleafe.project.repository;

import com.floodreleafe.project.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    // Level ke aadhar par search (Admin aur General use)
    List<Volunteer> findByLevelIgnoreCase(String level);

    // Status ke aadhar par search (PENDING ya APPROVED)
    List<Volunteer> findByStatus(String status);

    // Status aur Level dono ke aadhar par search (Public Portal ke liye)
    List<Volunteer> findByStatusAndLevelIgnoreCase(String status, String level);
}