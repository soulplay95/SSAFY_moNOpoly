package com.monopoly.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 공통 Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRes> handleException(Exception ex){
        ErrorRes response = new ErrorRes(ErrorCode.INTER_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // User - ID조회 실패. 찾는 ID가 없다.
    @ExceptionHandler(NoUserIdException.class)
    public ResponseEntity<ErrorRes> handleNoUserIdException(NoUserIdException ex){
        ErrorRes response = new ErrorRes(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

}
