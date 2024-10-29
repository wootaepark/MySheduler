package com.sparta.myscheduler.exceptions.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionCode {

	HAS_NOT_TOKEN(HttpStatus.BAD_REQUEST, "ERR_01", "Request has not token"),

	EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "ERR_02", "Request Token has expired"),

	NOT_VALID_TOKEN(HttpStatus.BAD_REQUEST, "ERR_03", "Request is not valid"),

	NOT_SUPPORT_TOKEN(HttpStatus.BAD_REQUEST, "ERR_04", "Request is not supported"),

	WRONG_TOKEN(HttpStatus.BAD_REQUEST, "ERR_05", "Request token is invalid"),

	INVALID_REQUEST_PARAMETER (HttpStatus.BAD_REQUEST, "ERR_06", "Invalid request parameter"),

	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "ERR_07", "Member not found"),

	NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "ERR_08", "Comment not found"),

	NOT_FOUND_SCHEDULE(HttpStatus.NOT_FOUND, "ERR_09", "Schedule not found"),

	NOT_ADMIN (HttpStatus.FORBIDDEN, "ERR_10", "Not admin"),

	NOT_MATCH_PASSWORD (HttpStatus.FORBIDDEN, "ERR_11", "Not match password"),

	DUPLICATED_EMAIL (HttpStatus.CONFLICT, "ERR_12", "Duplicated email"),

	DUPLICATED_USERNAME(HttpStatus.CONFLICT, "ERR_13", "Duplicated username"),

	ADMIN_PASSWORD_NOT_MATCH (HttpStatus.CONFLICT, "ERR_14", "Admin password not match"),;



	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	ExceptionCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

}
