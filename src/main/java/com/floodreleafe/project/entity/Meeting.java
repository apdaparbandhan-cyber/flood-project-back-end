package com.floodreleafe.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "meetings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Repository query method 'findByStatusOrderByMeetingDateDesc' ke liye
    private String meetingDate;

    private String date;
    private String location;
    private String status;        // UPCOMING ya RECENT

    @Column(columnDefinition = "TEXT")
    private String description;

    // Jab frontend se 'date' aaye to meetingDate me bhi save ho jaye
    @PrePersist
    @PreUpdate
    public void syncDates() {
        if (this.meetingDate == null && this.date != null) {
            this.meetingDate = this.date;
        } else if (this.date == null && this.meetingDate != null) {
            this.date = this.meetingDate;
        }
    }
}