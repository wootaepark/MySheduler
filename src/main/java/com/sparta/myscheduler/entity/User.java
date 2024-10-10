package com.sparta.myscheduler.entity;

import com.sparta.myscheduler.dto.user.UserRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserSchedule> userSchedules = new ArrayList<>();


    public User(UserRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setUser(this);
    }



}
