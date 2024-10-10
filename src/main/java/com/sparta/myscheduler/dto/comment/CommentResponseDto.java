package com.sparta.myscheduler.dto.comment;

import com.sparta.myscheduler.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();

    }

}
