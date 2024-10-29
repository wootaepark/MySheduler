package com.sparta.myscheduler.exceptions.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseExceptionDto {

	private HttpStatus httpStatus;
	private String code;
	private String message;
}
