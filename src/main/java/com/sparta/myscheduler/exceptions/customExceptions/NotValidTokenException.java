package com.sparta.myscheduler.exceptions.customExceptions;

import com.sparta.myscheduler.exceptions.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class NotValidTokenException extends RuntimeException{
	private final ExceptionCode exceptionCode;

	public NotValidTokenException(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

}
