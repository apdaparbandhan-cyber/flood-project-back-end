package com.floodreleafe.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site_alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String message;

    private boolean active;
}
