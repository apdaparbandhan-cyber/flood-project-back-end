package com.floodreleafe.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gallery_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GalleryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String tag;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String img;
}