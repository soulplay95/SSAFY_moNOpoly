package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 좌석 수정 실패 시 발생하는 예외
 */
@Getter
public class FailSeatUpdateException extends RuntimeException {

    private ErrorCode errorCode;

    public FailSeatUpdateException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
