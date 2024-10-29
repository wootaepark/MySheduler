package com.sparta.myscheduler;

import static com.sparta.myscheduler.exceptions.enums.ExceptionCode.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sparta.myscheduler.exceptions.customExceptions.NotFoundEntityException;
import com.sparta.myscheduler.exceptions.customExceptions.NotValidTokenException;
import com.sparta.myscheduler.exceptions.dto.NotValidRequestParameter;
import com.sparta.myscheduler.exceptions.dto.ResponseExceptionDto;
import com.sparta.myscheduler.exceptions.enums.ExceptionCode;

import lombok.extern.slf4j.Slf4j;
// Controller 에서 발생하는 예외에 대한 처리를 한다. (컨트롤러 도착 전에서의 예외는 ExceptionFilter 가 이를 수행함)
@RestControllerAdvice
@Slf4j(topic = "ControllerException")
public class GlobalExceptionHandler {

    @ExceptionHandler(NotValidTokenException.class)
    public ResponseEntity<Object> handleNotValidTokenException(NotValidTokenException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        log.error("{}: {}", exceptionCode, exceptionCode.getMessage());
        return ResponseEntity.status(exceptionCode.getHttpStatus())
            .body(makeResponseExceptionCode(exceptionCode));

    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<Object> handleNotFoundEntityException(NotFoundEntityException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        log.error("{}: {}", exceptionCode, exceptionCode.getMessage());
        return ResponseEntity.status(exceptionCode.getHttpStatus())
            .body(makeResponseExceptionCode(exceptionCode));


    }

    private ResponseExceptionDto makeResponseExceptionCode(ExceptionCode exceptionCode) {
        return ResponseExceptionDto.builder()
            .httpStatus(exceptionCode.getHttpStatus())
            .code(exceptionCode.getCode())
            .message(exceptionCode.getMessage())
            .build();
    }

    // 요청 시 입력 값이 일치 하지 않는 경우 (조건은 만족하지 않는 경우)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ExceptionCode exceptionCode = INVALID_REQUEST_PARAMETER;
        log.error("{}: {}", exceptionCode, exceptionCode.getMessage());
        return ResponseEntity.status(exceptionCode.getHttpStatus())
            .body(makeNotValidRequestParameter(e, exceptionCode));
    }

    private NotValidRequestParameter makeNotValidRequestParameter(BindException e,
        ExceptionCode exceptionCode) {
        List<NotValidRequestParameter.NotValidParameter> notValidParameters = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(NotValidRequestParameter.NotValidParameter::of)
            .toList();

        return NotValidRequestParameter.builder()
            .code(exceptionCode.name())
            .message(exceptionCode.getMessage())
            .notValidParameters(notValidParameters)
            .build();
    }


}
