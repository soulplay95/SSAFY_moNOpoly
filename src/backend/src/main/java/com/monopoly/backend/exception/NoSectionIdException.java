package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 찾는 섹션 ID의 좌석이 없으면 발생하는 예외
 */
@Getter
public class NoSectionIdException extends RuntimeException {

    private ErrorCode errorCode;

    public NoSectionIdException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
