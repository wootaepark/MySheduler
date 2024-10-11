package com.sparta.myscheduler.dto.userSchedule;

import com.sparta.myscheduler.entity.UserSchedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserScheduleResponseDto {
    private Long userScheduleId;
    private Long userId;
    private Long scheduleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserScheduleResponseDto(UserSchedule userSchedule) {
        this.userScheduleId = userSchedule.getId();
        this.userId = userSchedule.getUser().getId();
        this.scheduleId = userSchedule.getSchedule().getId();
        this.createdAt = userSchedule.getCreatedAt();
        this.updatedAt = userSchedule.getUpdatedAt();
    }
}
