package com.sparta.myscheduler.service;

import com.sparta.myscheduler.dto.schedule.ScheduleRequestDto;
import com.sparta.myscheduler.dto.schedule.ScheduleResponseDto;
import com.sparta.myscheduler.entity.Schedule;
import com.sparta.myscheduler.jwt.JwtUtil;
import com.sparta.myscheduler.repository.ScheduleRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto, String authorization) {

        if(isAdmin(authorization)) {
            Schedule schedule = findScheduleById(id);
            schedule.update(requestDto);
            return new ScheduleResponseDto(schedule);
        }
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }


    public void deleteSchedule(Long id, String authorization) {
        if(isAdmin(authorization)) {
            Schedule schedule = findScheduleById(id);
            scheduleRepository.delete(schedule);
        }

        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }


    private Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Schedule id : " + id + " not found")
        );
    }

    private boolean isAdmin(String authorization) {
        System.out.println("일정 서비스 시작");
        String token = jwtUtil.substringToken(authorization);
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String role = claims.get("auth", String.class);

        System.out.println("role : " + role);
        if (role == null || !role.equals("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: Admins only.");
        }
        System.out.println("일정 서비스 종료");
        return true;
    }
}
