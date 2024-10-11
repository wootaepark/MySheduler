package com.sparta.myscheduler.controller;

import com.sparta.myscheduler.dto.user.UserRequestDto;
import com.sparta.myscheduler.dto.user.UserResponseDto;
import com.sparta.myscheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user") // 모든 유저
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllUser());
    }
    
    @GetMapping("/user/{id}") // 특정 유저
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUser(id));
    }

    @PutMapping("/user/{id}") // 특정 유저 정보 수정
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUser(id, requestDto));
    }

    @DeleteMapping("/user/{id}") // 특정 유저 삭제
    public ResponseEntity<Long> deleteUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(userService.deleteUser(id));
    }

}
