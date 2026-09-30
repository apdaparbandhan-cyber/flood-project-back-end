package com.floodreleafe.project.controller;

import com.floodreleafe.project.entity.GalleryItem;
import com.floodreleafe.project.entity.Meeting;
import com.floodreleafe.project.entity.SiteAlert;
import com.floodreleafe.project.entity.Volunteer;
import com.floodreleafe.project.repository.GalleryRepository;
import com.floodreleafe.project.repository.MeetingRepository;
import com.floodreleafe.project.repository.SiteAlertRepository;
import com.floodreleafe.project.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PublicController {

    private final VolunteerRepository volunteerRepository;
    private final MeetingRepository meetingRepository;
    private final SiteAlertRepository siteAlertRepository;
    private final GalleryRepository galleryRepository;

    // Sirf APPROVED volunteers public list me dikhenge
    @GetMapping("/volunteers")
    public ResponseEntity<List<Volunteer>> getPublicVolunteers(
            @RequestParam(required = false, defaultValue = "all") String level) {
        if ("all".equalsIgnoreCase(level)) {
            return ResponseEntity.ok(volunteerRepository.findByStatus("APPROVED"));
        }
        return ResponseEntity.ok(volunteerRepository.findByStatusAndLevelIgnoreCase("APPROVED", level));
    }

    // Naya volunteer registration: Default status PENDING set hoga
    @PostMapping("/volunteers/register")
    public ResponseEntity<Volunteer> registerVolunteer(@RequestBody Volunteer volunteer) {
        volunteer.setStatus("PENDING");
        if (volunteer.getBadge() == null || volunteer.getBadge().isEmpty()) {
            volunteer.setBadge("सत्यापन प्रक्रियाधीन");
        }
        Volunteer saved = volunteerRepository.save(volunteer);
        return ResponseEntity.ok(saved);
    }

    // Public Alerts
    @GetMapping("/alerts")
    public ResponseEntity<List<SiteAlert>> getAlerts() {
        return ResponseEntity.ok(siteAlertRepository.findByActiveTrueOrderByIdDesc());
    }

    // Public Meetings
    @GetMapping("/meetings")
    public ResponseEntity<List<Meeting>> getMeetings() {
        return ResponseEntity.ok(meetingRepository.findAll());
    }

    // Public Gallery
    @GetMapping("/gallery")
    public ResponseEntity<List<GalleryItem>> getGallery() {
        return ResponseEntity.ok(galleryRepository.findAll());
    }
}