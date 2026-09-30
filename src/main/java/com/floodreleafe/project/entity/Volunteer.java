package com.floodreleafe.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "volunteers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mobile;

    private String role;
    private String level;     // national, state, district
    private String state;
    private String district;
    private String location;
    private String skill;
    private String badge;

    @Column(nullable = false)
    private String status;    // PENDING, APPROVED, REJECTED

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String photo;     // Base64 Data URL format

    private String appliedAt;
}