package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 좌석 정보 등록 시 ID가 중복되면 발생하는 예외
 */
@Getter
public class SeatIdDuplicatedException extends RuntimeException {

    private ErrorCode errorCode;

    public SeatIdDuplicatedException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
