package com.sparta.myscheduler.entity;


import com.sparta.myscheduler.dto.schedule.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
