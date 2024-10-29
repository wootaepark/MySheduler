package com.sparta.myscheduler.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sparta.myscheduler.config.PasswordEncoder;
import com.sparta.myscheduler.dto.auth.LoginRequestDto;
import com.sparta.myscheduler.dto.auth.SignupRequestDto;
import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.entity.UserRoleEnum;
import com.sparta.myscheduler.exceptions.customExceptions.DuplicatedUserException;
import com.sparta.myscheduler.exceptions.customExceptions.NotFoundEntityException;
import com.sparta.myscheduler.exceptions.customExceptions.NotMatchPasswordException;
import com.sparta.myscheduler.exceptions.enums.ExceptionCode;
import com.sparta.myscheduler.jwt.JwtUtil;
import com.sparta.myscheduler.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    // 일반 사용자, 관리 사용자를 구별하기 위한 토큰 (실제로는 이렇게 하지는 않음)
    // 실제로는 관리자 페이지 또는 승인 과정을 이용

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        if(!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            throw new NotMatchPasswordException(ExceptionCode.NOT_MATCH_PASSWORD);
        }

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new DuplicatedUserException(ExceptionCode.DUPLICATED_USERNAME);
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new DuplicatedUserException(ExceptionCode.DUPLICATED_EMAIL);
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new NotMatchPasswordException(ExceptionCode.ADMIN_PASSWORD_NOT_MATCH);
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, email, role);
        userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundEntityException(ExceptionCode.NOT_FOUND_USER));

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new NotMatchPasswordException(ExceptionCode.NOT_MATCH_PASSWORD);
        }

        // 인증 완료 JWT 생성 및 쿠키 저장 그리고 Response 객체에 추가해서 반환
        String token = jwtUtil.createToken(email, user.getUsername(),user.getRole());
        jwtUtil.addJwtToCookie(token, res);
    }
}
