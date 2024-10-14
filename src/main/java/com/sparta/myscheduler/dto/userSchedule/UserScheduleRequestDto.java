package com.sparta.myscheduler.dto.userSchedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class UserScheduleRequestDto {

    @NotNull(message = "userId cannot be blank Null")
    @Positive(message = "userId cannot be negative")
    private Long userId;

    @NotNull(message = "scheduleId cannot be blank Null")
    @Positive(message = "scheduleId cannot be negative")
    private Long scheduleId;

}
