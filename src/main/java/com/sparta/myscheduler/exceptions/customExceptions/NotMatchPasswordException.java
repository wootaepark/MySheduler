package com.sparta.myscheduler.exceptions.customExceptions;

import com.sparta.myscheduler.exceptions.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class NotMatchPasswordException extends RuntimeException {
	private final ExceptionCode exceptionCode;
	public NotMatchPasswordException(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}
