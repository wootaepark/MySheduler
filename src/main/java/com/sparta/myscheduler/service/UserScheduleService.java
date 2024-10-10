package com.sparta.myscheduler.service;


import com.sparta.myscheduler.dto.userSchedule.UserScheduleRequestDto;
import com.sparta.myscheduler.entity.Schedule;
import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.entity.UserSchedule;
import com.sparta.myscheduler.repository.ScheduleRepository;
import com.sparta.myscheduler.repository.UserRepository;
import com.sparta.myscheduler.repository.UserScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserScheduleService {

    private final UserScheduleRepository userScheduleRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void createUserSchedule(UserScheduleRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                        .orElseThrow(()->new IllegalArgumentException("User id" + requestDto.getUserId() + "not found"));

        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(()->new IllegalArgumentException("Schedule id" + requestDto.getScheduleId() + "not found"));

        UserSchedule userSchedule = new UserSchedule(user, schedule);
        userScheduleRepository.save(userSchedule);
    }
}
