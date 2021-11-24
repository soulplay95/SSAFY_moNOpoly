package com.monopoly.backend.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * exception 발생 시 응답
 */
@Getter
@Setter
public class ErrorRes {

    private Integer status;
    private String message;

    public ErrorRes(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }

}
