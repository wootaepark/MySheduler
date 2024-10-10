package com.sparta.myscheduler.service;

import com.sparta.myscheduler.dto.user.UserRequestDto;
import com.sparta.myscheduler.dto.user.UserResponseDto;
import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto requestDto) {
        return new UserResponseDto(userRepository.save(new User(requestDto)));
    }
}
