package com.floodreleafe.project.service;

import com.floodreleafe.project.entity.SiteAlert;
import com.floodreleafe.project.repository.SiteAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final SiteAlertRepository siteAlertRepository;

    public List<SiteAlert> getActiveAlerts() {
        return siteAlertRepository.findByActiveTrue();
    }

    public List<SiteAlert> getAllAlerts() {
        return siteAlertRepository.findAll();
    }

    public SiteAlert saveAlert(SiteAlert alert) {
        return siteAlertRepository.save(alert);
    }

    public void deleteAlert(Long id) {
        siteAlertRepository.deleteById(id);
    }
}