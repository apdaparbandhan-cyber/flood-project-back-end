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
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {

    private final VolunteerRepository volunteerRepository;
    private final MeetingRepository meetingRepository;
    private final SiteAlertRepository siteAlertRepository;
    private final GalleryRepository galleryRepository;

    // Sabhi volunteers (PENDING aur APPROVED dono)
    @GetMapping("/volunteers")
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerRepository.findAll());
    }

    // Volunteer Approve Karna
    @PostMapping("/volunteers/{id}/approve")
    public ResponseEntity<?> approveVolunteer(@PathVariable Long id) {
        return volunteerRepository.findById(id).map(volunteer -> {
            volunteer.setStatus("APPROVED");
            volunteer.setBadge("सत्यापित सदस्य");
            volunteerRepository.save(volunteer);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // Direct Admin se volunteer add karna
    @PostMapping("/volunteers")
    public ResponseEntity<Volunteer> addVolunteer(@RequestBody Volunteer volunteer) {
        volunteer.setStatus("APPROVED");
        return ResponseEntity.ok(volunteerRepository.save(volunteer));
    }

    // Volunteer Delete / Reject
    @DeleteMapping("/volunteers/{id}")
    public ResponseEntity<?> deleteVolunteer(@PathVariable Long id) {
        volunteerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Alert Update
    @PostMapping("/alerts")
    public ResponseEntity<SiteAlert> createOrUpdateAlert(@RequestBody SiteAlert alert) {
        // पुराने सभी अलर्ट्स को inactive कर दें
        List<SiteAlert> oldAlerts = siteAlertRepository.findAll();
        for (SiteAlert old : oldAlerts) {
            old.setActive(false);
        }
        siteAlertRepository.saveAll(oldAlerts);

        // नया अलर्ट हमेशा active सेव होगा
        alert.setActive(true);
        SiteAlert saved = siteAlertRepository.save(alert);
        return ResponseEntity.ok(saved);
    }

    // Meeting CMS
    @PostMapping("/meetings")
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
        return ResponseEntity.ok(meetingRepository.save(meeting));
    }

    @DeleteMapping("/meetings/{id}")
    public ResponseEntity<?> deleteMeeting(@PathVariable Long id) {
        meetingRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Gallery CMS
    @PostMapping("/gallery")
    public ResponseEntity<GalleryItem> addGalleryItem(@RequestBody GalleryItem item) {
        return ResponseEntity.ok(galleryRepository.save(item));
    }

    @DeleteMapping("/gallery/{id}")
    public ResponseEntity<?> deleteGalleryItem(@PathVariable Long id) {
        galleryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}