package com.sparta.myscheduler.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Content must be in 100 characters")
    private String title;

    @NotBlank(message = "Schedule content cannot be blank")
    @Size(max = 1000, message = "Content must be in 1000 characters")
    private String content;

    @NotNull(message = "Date cannot be null")
    private LocalDate scheduleDate;
    
}
