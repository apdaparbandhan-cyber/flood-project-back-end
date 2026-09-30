package com.floodreleafe.project.repository;

import com.floodreleafe.project.entity.GalleryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryItem, Long> {
}