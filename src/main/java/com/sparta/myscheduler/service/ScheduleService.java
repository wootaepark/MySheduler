package com.sparta.myscheduler.service;

import com.sparta.myscheduler.dto.ScheduleRequestDto;
import com.sparta.myscheduler.dto.ScheduleResponseDto;
import com.sparta.myscheduler.entity.Schedule;
import com.sparta.myscheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        return new ScheduleResponseDto(scheduleRepository.save(new Schedule(requestDto)));
    }

    public List<ScheduleResponseDto> getSchedule() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::new).toList();
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = findById(id);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findById(id);
        schedule.update(requestDto);
        return new ScheduleResponseDto(schedule);
    }


    public void deleteSchedule(Long id) {
        Schedule schedule = findById(id);
        scheduleRepository.delete(schedule);
    }


    private Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Schedule id : " + id  + " not found")
        );
    }
}
