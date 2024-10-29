package com.sparta.myscheduler.dto.schedule;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Content must be in 100 characters")
    private String title;

    @NotBlank(message = "Schedule content cannot be blank")
    @Size(max = 1000, message = "Content must be in 1000 characters")
    private String content;

    @NotNull(message = "Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduleDate;
    
}
