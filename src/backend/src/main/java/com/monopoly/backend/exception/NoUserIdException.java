package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 찾는 유저 ID의 유저가 없으면 발생하는 예외
 */
@Getter
public class NoUserIdException extends RuntimeException {

    private ErrorCode errorCode;

    public NoUserIdException(ErrorCode errorCode) {
//        super(message);
        this.errorCode = errorCode;
    }
    
}
