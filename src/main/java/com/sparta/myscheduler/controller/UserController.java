package com.sparta.myscheduler.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.myscheduler.dto.user.UserRequestDto;
import com.sparta.myscheduler.dto.user.UserResponseDto;
import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @GetMapping("/user") // 모든 유저
    public ResponseEntity<List<UserResponseDto>> getAllUser(
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllUser());
    }
    
    @GetMapping("/user/{id}") // 특정 유저
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUser(id));
    }

    @PutMapping("/user/{id}") // 특정 유저 정보 수정
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUser(id, requestDto));
    }

    @DeleteMapping("/user/delete") // 특정 유저 삭제
    public void deleteUser(
        @RequestAttribute("user") User user){
        userService.deleteUser(user);
    }

}
