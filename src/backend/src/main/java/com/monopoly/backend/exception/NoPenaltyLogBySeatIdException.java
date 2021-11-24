package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 해당 좌석 ID의 패널티 로그 목록이 존재하지 않음
 */
@Getter
public class NoPenaltyLogBySeatIdException extends RuntimeException {

    private ErrorCode errorCode;

    public NoPenaltyLogBySeatIdException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
