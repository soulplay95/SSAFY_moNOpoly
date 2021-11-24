package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 공지사항 조회수 1증가 실패
 */
@Getter
public class FailNoticeHitException extends RuntimeException {

    private ErrorCode errorCode;

    public FailNoticeHitException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
