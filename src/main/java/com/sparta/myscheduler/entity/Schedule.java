package com.sparta.myscheduler.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sparta.myscheduler.dto.schedule.ScheduleRequestDto;
import com.sparta.myscheduler.exceptions.customExceptions.NotValidTokenException;
import com.sparta.myscheduler.exceptions.enums.ExceptionCode;
import com.sparta.myscheduler.jwt.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private LocalDate scheduleDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<UserSchedule> userSchedules = new ArrayList<>();



    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.scheduleDate = requestDto.getScheduleDate();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setSchedule(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setSchedule(null);
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.scheduleDate = requestDto.getScheduleDate();
    }

    public void addSchedule(User user) {
        UserSchedule userSchedule = new UserSchedule(user, this);
        userSchedules.add(userSchedule);
    }

    public boolean isAdmin(String authorization, JwtUtil jwtUtil) {
        System.out.println("일정 서비스 시작");
        String token = jwtUtil.substringToken(authorization);
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String role = claims.get("auth", String.class);

        System.out.println("role : " + role);
        if (role == null || !role.equals("ADMIN")) {
            throw new NotValidTokenException(ExceptionCode.NOT_ADMIN);
        }
        System.out.println("일정 서비스 종료");
        return true;
    }
}
