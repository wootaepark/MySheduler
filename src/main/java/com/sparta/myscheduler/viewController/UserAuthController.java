package com.sparta.myscheduler.viewController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.myscheduler.dto.auth.LoginRequestDto;
import com.sparta.myscheduler.dto.auth.SignupRequestDto;
import com.sparta.myscheduler.service.AuthUserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserAuthController {

    private final AuthUserService authUserService;

    @PostMapping("/auth/signup")
    public String signUp(@Valid @RequestBody SignupRequestDto requestDto) {
        authUserService.signup(requestDto);
        return "회원 가입 완료";
    }

    @PostMapping("/auth/login")
    public String login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        authUserService.login(requestDto, res);
        return "로그인 성공";
    }
}