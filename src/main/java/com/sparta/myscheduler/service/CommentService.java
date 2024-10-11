package com.sparta.myscheduler.service;

import com.sparta.myscheduler.dto.comment.CommentRequestDto;
import com.sparta.myscheduler.dto.comment.CommentResponseDto;
import com.sparta.myscheduler.entity.Comment;
import com.sparta.myscheduler.entity.Schedule;
import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.repository.CommentRepository;
import com.sparta.myscheduler.repository.ScheduleRepository;
import com.sparta.myscheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Schedule schedule = findScheduleById(requestDto.getScheduleId());
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User id " + requestDto.getUserId()+ "not found"));
        Comment comment = new Comment(requestDto);
        schedule.addComment(comment);
        user.addComment(comment);
        return new CommentResponseDto(commentRepository.save(comment));
    }

    @Transactional
    public List<CommentResponseDto> getComments(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        return schedule.getComments().stream().map(CommentResponseDto::new).toList();
    }

    @Transactional
    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll(); // 모든 댓글을 가져옴
        return comments.stream().map(CommentResponseDto::new).toList();
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Comment id " + id + "not found"));
        comment.update(requestDto);
        return new CommentResponseDto(commentRepository.save(comment));
    }



    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment id " + id + "not found"));
        commentRepository.delete(comment);
    }

    private Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Schedule id : " + id  + " not found")
        );
    }
}
