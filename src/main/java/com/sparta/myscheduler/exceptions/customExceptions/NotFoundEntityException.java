package com.sparta.myscheduler.exceptions.customExceptions;

import com.sparta.myscheduler.exceptions.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class NotFoundEntityException extends RuntimeException {

	private final ExceptionCode exceptionCode;

	public NotFoundEntityException(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}
