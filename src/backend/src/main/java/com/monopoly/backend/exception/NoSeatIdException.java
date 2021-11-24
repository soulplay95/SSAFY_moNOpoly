package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 찾는 좌석 ID의 좌석이 없으면 발생하는 예외
 */
@Getter
public class NoSeatIdException extends RuntimeException {

    private ErrorCode errorCode;

    public NoSeatIdException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
