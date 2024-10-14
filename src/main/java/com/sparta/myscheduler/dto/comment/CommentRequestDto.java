package com.sparta.myscheduler.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotNull(message = "scheduleId cannot be blank Null")
    @Positive(message = "scheduleId cannot be negative")
    private Long scheduleId;

    @NotNull(message = "userId cannot be blank Null")
    @Positive(message = "userId cannot be negative")
    private Long userId;

    @NotBlank(message = "content cannot be blank")
    @Size(max = 1000, message = "Content must be in 1000 characters")
    private String content;
}
