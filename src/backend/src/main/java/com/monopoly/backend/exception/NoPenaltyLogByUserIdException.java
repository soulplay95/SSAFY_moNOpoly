package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 해당 유저 ID의 패널티 로그 목록이 존재하지 않음
 */
@Getter
public class NoPenaltyLogByUserIdException extends RuntimeException {

    private ErrorCode errorCode;

    public NoPenaltyLogByUserIdException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
