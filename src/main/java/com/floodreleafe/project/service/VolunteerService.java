package com.floodreleafe.project.service;

import com.floodreleafe.project.entity.Volunteer;
import com.floodreleafe.project.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    // Public list: Sirf APPROVED members dikhenge
    public List<Volunteer> getApprovedVolunteersByLevel(String level) {
        if (level == null || level.trim().isEmpty() || "all".equalsIgnoreCase(level)) {
            return volunteerRepository.findByStatus("APPROVED");
        }
        return volunteerRepository.findByStatusAndLevelIgnoreCase("APPROVED", level.trim());
    }

    // Admin list: Sabhi members level ke anusaar
    public List<Volunteer> getVolunteersByLevel(String level) {
        if (level == null || level.trim().isEmpty() || "all".equalsIgnoreCase(level)) {
            return volunteerRepository.findAll();
        }
        return volunteerRepository.findByLevelIgnoreCase(level.trim());
    }

    // Sabhi volunteers (PENDING + APPROVED)
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    // Naya volunteer save karna
    public Volunteer saveVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    // Admin dwara volunteer approve karna
    public boolean approveVolunteer(Long id) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        if (optionalVolunteer.isPresent()) {
            Volunteer volunteer = optionalVolunteer.get();
            volunteer.setStatus("APPROVED");
            volunteer.setBadge("सत्यापित सदस्य");
            volunteerRepository.save(volunteer);
            return true;
        }
        return false;
    }

    // Volunteer delete / reject karna
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}