package com.floodreleafe.project.service;


import com.floodreleafe.project.entity.Meeting;
import com.floodreleafe.project.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    @Transactional(readOnly = true)
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @Transactional
    public Meeting createMeeting(Meeting meeting) {
        if (meeting.getStatus() == null) meeting.setStatus("UPCOMING");
        return meetingRepository.save(meeting);
    }

    @Transactional
    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long countMeetings() {
        return meetingRepository.count();
    }
}