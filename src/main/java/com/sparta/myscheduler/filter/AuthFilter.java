package com.sparta.myscheduler.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.exceptions.customExceptions.NotFoundEntityException;
import com.sparta.myscheduler.exceptions.customExceptions.NotValidTokenException;
import com.sparta.myscheduler.exceptions.enums.ExceptionCode;
import com.sparta.myscheduler.jwt.JwtUtil;
import com.sparta.myscheduler.repository.UserRepository;

import io.jsonwebtoken.Claims;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "AuthFilter")
@Component
@Order(3)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                (url.startsWith("/api/auth") ||url.startsWith("/css") || url.startsWith("/js") || url.startsWith("/favicon"))
        ) {
            log.info("인증 처리를 하지 않는 URL : " + url);
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromHeader(httpServletRequest);
            log.info("토큰 값 : " + tokenValue);


            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                log.info("검증 시작");
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                jwtUtil.validateToken(token);


                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                User user = userRepository.findByEmail(info.getSubject()).orElseThrow(() ->
                        new NotFoundEntityException(ExceptionCode.NOT_FOUND_USER)
                );

                request.setAttribute("user", user);
                log.info("유저 인증 성공");
                chain.doFilter(request, response); // 다음 Filter 로 이동
            }

            else
                throw new NotValidTokenException(ExceptionCode.HAS_NOT_TOKEN);
        }
    }


}

