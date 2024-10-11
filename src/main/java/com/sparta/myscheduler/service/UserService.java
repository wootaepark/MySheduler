package com.sparta.myscheduler.service;

import com.sparta.myscheduler.dto.user.UserRequestDto;
import com.sparta.myscheduler.dto.user.UserResponseDto;
import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto requestDto) {
        return new UserResponseDto(userRepository.save(new User(requestDto)));
    }

    public List<UserResponseDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDto::new).toList();
    }


    public UserResponseDto getUser(Long id) {
        return new UserResponseDto(userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User id" + id + " not found")));
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User id" + id + " not found"));
        user.update(requestDto);
        return new UserResponseDto(userRepository.save(user));
    }


    public Long deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User id" + id + " not found"));
        userRepository.delete(user);
        return id;
    }
}
