package com.sparta.myscheduler.dto.comment;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long scheduleId;
    private Long userId;
    private String content;
}
