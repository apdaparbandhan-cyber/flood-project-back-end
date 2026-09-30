package com.floodreleafe.project.repository;

import com.floodreleafe.project.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    // Original method jo call ho raha hai
    List<Meeting> findByStatusOrderByMeetingDateDesc(String status);

    List<Meeting> findByStatus(String status);
}