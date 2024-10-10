package com.sparta.myscheduler.dto.userSchedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserScheduleResponseDto {
    private Long userScheduleId;
    private Long userId;
    private Long scheduleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
