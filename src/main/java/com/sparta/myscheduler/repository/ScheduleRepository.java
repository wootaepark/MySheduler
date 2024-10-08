package com.sparta.myscheduler.repository;

import com.sparta.myscheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
