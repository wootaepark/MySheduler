package com.sparta.myscheduler.controller;

import com.sparta.myscheduler.dto.userSchedule.UserScheduleRequestDto;
import com.sparta.myscheduler.dto.userSchedule.UserScheduleResponseDto;
import com.sparta.myscheduler.service.UserScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserScheduleController {

    private final UserScheduleService userScheduleService;


    @PostMapping("/userSchedule")
    public ResponseEntity<UserScheduleResponseDto> createUserSchedule(@RequestBody UserScheduleRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                        .body( userScheduleService.createUserSchedule(requestDto));
    };

    @GetMapping("/userSchedule")
    public ResponseEntity<List<UserScheduleResponseDto>> getUserSchedule() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userScheduleService.getUserSchedule());
    }

    @DeleteMapping("/userSchedule")
    public ResponseEntity<Long> deleteUserSchedule(@RequestBody UserScheduleRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(userScheduleService.deleteUserSchedule(requestDto));
    }

}
