package com.sparta.myscheduler.controller;

import com.sparta.myscheduler.dto.comment.CommentRequestDto;
import com.sparta.myscheduler.dto.comment.CommentResponseDto;
import com.sparta.myscheduler.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(commentService.createComment(requestDto));
    }

    @GetMapping("/comment/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments(scheduleId));
    }

    /*@PutMapping("/comment/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentId, requestDto));
    }*/





}
