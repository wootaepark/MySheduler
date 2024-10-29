package com.sparta.myscheduler.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.myscheduler.dto.user.UserRequestDto;
import com.sparta.myscheduler.dto.user.UserResponseDto;
import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.exceptions.customExceptions.NotFoundEntityException;
import com.sparta.myscheduler.exceptions.enums.ExceptionCode;
import com.sparta.myscheduler.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public List<UserResponseDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDto::new).toList();
    }


    public UserResponseDto getUser(Long id) {
        return new UserResponseDto(userRepository.findById(id)
                .orElseThrow(()->new NotFoundEntityException(ExceptionCode.NOT_FOUND_USER)));
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User id" + id + " not found"));
        user.update(requestDto);
        return new UserResponseDto(userRepository.save(user));
    }


    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
