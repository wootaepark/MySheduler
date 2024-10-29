package com.sparta.myscheduler.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.myscheduler.exceptions.customExceptions.NotValidTokenException;
import com.sparta.myscheduler.exceptions.dto.ResponseExceptionDto;
import com.sparta.myscheduler.exceptions.enums.ExceptionCode;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "FilterException")
@Component
@Order(2)
public class ExceptionFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse)response;

		try {
			chain.doFilter(request, response);

		} catch (NotValidTokenException e) {
			setExceptionToResponse(httpResponse, e.getExceptionCode());
		}
	}

	private void setExceptionToResponse(HttpServletResponse httpServletResponse, ExceptionCode exceptionCode) throws
		IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		httpServletResponse.setStatus(exceptionCode.getHttpStatus().value());
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

		ResponseExceptionDto responseExceptionDto = ResponseExceptionDto.builder()
			.httpStatus(exceptionCode.getHttpStatus())
			.code(exceptionCode.getCode())
			.message(exceptionCode.getMessage())
			.build();

		log.error("{} : {}: {}", exceptionCode.getHttpStatus(), exceptionCode.getCode(), exceptionCode.getMessage());

		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseExceptionDto));
	}
}
