package com.sparta.myscheduler.viewController;

import com.sparta.myscheduler.dto.auth.LoginRequestDto;
import com.sparta.myscheduler.dto.auth.SignupRequestDto;
import com.sparta.myscheduler.service.AuthUserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserAuthController {

    private final AuthUserService authUserService;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signUp(@Valid SignupRequestDto requestDto) {
        authUserService.signup(requestDto);

        return "redirect:/api/user/login-page";
    }

    @PostMapping("/user/login")
    public String login(@Valid LoginRequestDto requestDto, HttpServletResponse res) {
        try {
            authUserService.login(requestDto, res);
        } catch (Exception e) {
            return "redirect:/api/user/login-page?error";
        }

        return "redirect:/";
    }
}