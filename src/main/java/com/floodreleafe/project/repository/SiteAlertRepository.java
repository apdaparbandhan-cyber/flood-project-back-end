package com.floodreleafe.project.repository;

import com.floodreleafe.project.entity.SiteAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteAlertRepository extends JpaRepository<SiteAlert, Long> {

    // Is method ko add karein (Line 21 ka error isi se solve hoga)
    List<SiteAlert> findByActiveTrue();

    // Latest active alerts order me fetch karne ke liye (Optional & Recommended)
    List<SiteAlert> findByActiveTrueOrderByIdDesc();
}