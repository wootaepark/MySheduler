package com.sparta.myscheduler.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sparta.myscheduler.dto.schedule.ScheduleRequestDto;
import com.sparta.myscheduler.dto.schedule.ScheduleResponseDto;
import com.sparta.myscheduler.entity.Schedule;
import com.sparta.myscheduler.exceptions.customExceptions.NotFoundEntityException;
import com.sparta.myscheduler.exceptions.enums.ExceptionCode;
import com.sparta.myscheduler.jwt.JwtUtil;
import com.sparta.myscheduler.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {


    private final JwtUtil jwtUtil;
    private final ScheduleRepository scheduleRepository;



    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        return new ScheduleResponseDto(scheduleRepository.save(new Schedule(requestDto)));
    }

    public Page<ScheduleResponseDto> getSchedule(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        return scheduleRepository.findAll(pageable)
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getScheduleDate(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ));
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = findScheduleById(id);
        return new ScheduleResponseDto(schedule);
    }


    // 다른 도메인의 update, delete 와 비교해보기 (도메인에 일정 역할 위임함)
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto, String authorization) {

        Schedule schedule = findScheduleById(id);
        if(!schedule.isAdmin(authorization,jwtUtil)) {
            schedule.update(requestDto);
            return new ScheduleResponseDto(schedule);
        }
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }


    public void deleteSchedule(Long id, String authorization) {
        Schedule schedule = findScheduleById(id);
        if(schedule.isAdmin(authorization, jwtUtil)) {
            scheduleRepository.delete(schedule);
        }
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }


    public Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new NotFoundEntityException(ExceptionCode.NOT_FOUND_SCHEDULE)
        );
    }


}
