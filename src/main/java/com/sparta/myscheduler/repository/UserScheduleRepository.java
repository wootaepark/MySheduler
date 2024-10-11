package com.sparta.myscheduler.repository;

import com.sparta.myscheduler.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
    UserSchedule findByUserIdAndScheduleId(Long userId, Long scheduleId);
}
