package com.sparta.myscheduler.exceptions.customExceptions;

import com.sparta.myscheduler.exceptions.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class DuplicatedUserException extends RuntimeException {
	private final ExceptionCode exceptionCode;
	public DuplicatedUserException(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}
