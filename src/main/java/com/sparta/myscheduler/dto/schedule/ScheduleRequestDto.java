package com.sparta.myscheduler.dto.schedule;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {

    private String title;
    private String content;
    private LocalDate scheduleDate;
    
}
