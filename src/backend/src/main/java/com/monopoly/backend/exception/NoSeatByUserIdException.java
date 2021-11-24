package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 해당 유저 ID의 좌석이 존재하지 않으면 발생
 */
@Getter
public class NoSeatByUserIdException extends RuntimeException {

    private ErrorCode errorCode;

    public NoSeatByUserIdException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
