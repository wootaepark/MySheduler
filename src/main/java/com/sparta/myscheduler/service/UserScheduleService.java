package com.sparta.myscheduler.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.myscheduler.dto.userSchedule.UserScheduleRequestDto;
import com.sparta.myscheduler.dto.userSchedule.UserScheduleResponseDto;
import com.sparta.myscheduler.entity.Schedule;
import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.entity.UserSchedule;
import com.sparta.myscheduler.exceptions.customExceptions.NotFoundEntityException;
import com.sparta.myscheduler.exceptions.enums.ExceptionCode;
import com.sparta.myscheduler.repository.ScheduleRepository;
import com.sparta.myscheduler.repository.UserRepository;
import com.sparta.myscheduler.repository.UserScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserScheduleService {

	private final UserScheduleRepository userScheduleRepository;
	private final UserRepository userRepository;
	private final ScheduleRepository scheduleRepository;

	@Transactional
	public UserScheduleResponseDto createUserSchedule(UserScheduleRequestDto requestDto) {
		User user = userRepository.findById(requestDto.getUserId())
			.orElseThrow(() -> new NotFoundEntityException(ExceptionCode.NOT_FOUND_USER));

		Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
			.orElseThrow(() -> new NotFoundEntityException(ExceptionCode.NOT_FOUND_SCHEDULE));

		UserSchedule userSchedule = new UserSchedule(user, schedule);
		return new UserScheduleResponseDto(userScheduleRepository.save(userSchedule));
	}

	public List<UserScheduleResponseDto> getUserSchedule() {
		List<UserSchedule> userSchedules = userScheduleRepository.findAll();
		return userSchedules.stream().map(UserScheduleResponseDto::new).toList();
	}

	public Long deleteUserSchedule(UserScheduleRequestDto requestDto) {
		UserSchedule userSchedule = userScheduleRepository.findByUserIdAndScheduleId(requestDto.getUserId(),
			requestDto.getScheduleId());
		userScheduleRepository.delete(userSchedule);
		return userSchedule.getId();
	}
}
