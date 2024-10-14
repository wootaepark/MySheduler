package com.sparta.myscheduler.controller;


import com.sparta.myscheduler.dto.schedule.ScheduleRequestDto;
import com.sparta.myscheduler.dto.schedule.ScheduleResponseDto;
import com.sparta.myscheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {


    private final ScheduleService scheduleService;

    // 일정 등록 api
    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(requestDto));
    }

    // 일정 모두 보기 api
    @GetMapping("/schedule")
    public ResponseEntity<Page<ScheduleResponseDto>> getSchedule(
            @RequestParam (required = false, defaultValue = "0") int page,
            @RequestParam (required = false, defaultValue = "10") int size){
        return ResponseEntity.ok(scheduleService.getSchedule(page, size));
    }

    // 특정 일정 보기 api
    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));

    }

    // 일정 수정 api
    @PutMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto));
    }

    // 일정 삭제 api
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Long> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }


}
