package com.sparta.myscheduler.controller;

import com.sparta.myscheduler.dto.userSchedule.UserScheduleRequestDto;
import com.sparta.myscheduler.service.UserScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserScheduleController {

    private final UserScheduleService userScheduleService;


    @PostMapping("/userSchedule")
    public void createUserSchedule(@RequestBody UserScheduleRequestDto requestDto) {
        userScheduleService.createUserSchedule(requestDto);
    };

}
