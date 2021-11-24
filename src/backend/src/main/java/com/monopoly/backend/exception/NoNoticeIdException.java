package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 공지사항 ID가 없음
 */
@Getter
public class NoNoticeIdException extends RuntimeException {

    private ErrorCode errorCode;

    public NoNoticeIdException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
