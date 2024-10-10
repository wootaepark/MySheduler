package com.sparta.myscheduler.controller;

import com.sparta.myscheduler.dto.user.UserRequestDto;
import com.sparta.myscheduler.dto.user.UserResponseDto;
import com.sparta.myscheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(requestDto));
    }

    // @GetMapping("/user") // 모든 유저
    
    // @GetMapping("/user/{id}") // 특정 유저

    // @PutMapping("/user/{id}") // 특정 유저 정보 수정

    // @DeleteMapping("/user/{id}") // 특정 유저 삭제

}
