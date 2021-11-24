package com.monopoly.backend.exception;

import lombok.Getter;

/**
 * 해당 도서관 ID의 공지사항이 없음
 */
@Getter
public class NoNoticeByLibraryIdException extends RuntimeException {

    private ErrorCode errorCode;

    public NoNoticeByLibraryIdException(ErrorCode errorCode) {
//        super(message);
        this.errorCode = errorCode;
    }
    
}
