package com.sparta.myscheduler.exceptions.customExceptions;

import com.sparta.myscheduler.exceptions.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class NotHaveAdminException extends RuntimeException {

	private final ExceptionCode exceptionCode;

	public NotHaveAdminException(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}
