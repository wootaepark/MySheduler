package com.sparta.myscheduler.service;

import com.sparta.myscheduler.dto.schedule.ScheduleRequestDto;
import com.sparta.myscheduler.dto.schedule.ScheduleResponseDto;
import com.sparta.myscheduler.entity.Schedule;
import com.sparta.myscheduler.repository.CommentRepository;
import com.sparta.myscheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        return new ScheduleResponseDto(scheduleRepository.save(new Schedule(requestDto)));
    }

    public List<ScheduleResponseDto> getSchedule() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::new).toList();
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = findScheduleById(id);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findScheduleById(id);
        schedule.update(requestDto);
        return new ScheduleResponseDto(schedule);
    }


    public void deleteSchedule(Long id) {
        Schedule schedule = findScheduleById(id);
        scheduleRepository.delete(schedule);

    }


    private Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Schedule id : " + id  + " not found")
        );
    }
}
