package com.sparta.myscheduler.exceptions.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionCode {

	HAS_NOT_TOKEN(HttpStatus.BAD_REQUEST, "ERR_01", "Request has not token"),

	EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "ERR_02", "Request Token has expired"),

	INVALID_REQUEST_PARAMETER (HttpStatus.BAD_REQUEST, "ERR_03", "Invalid request parameter"),

	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "ERR_04", "Member not found"),;



	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	ExceptionCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

}
