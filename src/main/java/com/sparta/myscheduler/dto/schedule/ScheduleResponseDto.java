package com.sparta.myscheduler.dto.schedule;


import com.sparta.myscheduler.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDate scheduleDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.scheduleDate = schedule.getScheduleDate();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

    public ScheduleResponseDto( Long id, String title, String content, LocalDate scheduleDate,
                               LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.scheduleDate = scheduleDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
