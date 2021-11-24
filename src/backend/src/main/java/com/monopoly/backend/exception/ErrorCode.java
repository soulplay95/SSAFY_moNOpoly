package com.monopoly.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 에러 코드 목록
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 공통 에러 코드
    BAD_REQUEST(400, "BAD REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    NOT_FOUND(404,"NOT FOUND"),
    INTER_SERVER_ERROR(500,"INTER SERVER ERROR"),
    // 유저 관련 에러
    USER_NO_ID(400,"해당 ID를 가진 유저가 존재하지 않습니다."),
    // 좌석 관련 에러
    SEAT_NO_ID(400, "해당 ID의 좌석이 존재하지 않습니다."),
    SEAT_NO_SECTION_ID(400, "해당 Section ID의 좌석이 존재하지 않습니다."),
    SEAT_FAIL_UPDATE(500, "좌석 정보 수정에 실패했습니다."),
    SEAT_ID_DUPLICATED(400, "이미 해당 ID의 좌석이 존재합니다."),
    SEAT_NO_USER_ID(400, "해당 ID를 가진 유저의 좌석이 존재하지 않습니다."),
    // 패널티 로그 관련 에러
    PENALTY_LOG_NO_USER_ID(404, "해당 ID를 가진 유저의 패널티 로그가 존재하지 않습니다."),
    PENALTY_LOG_NO_SEAT_ID(404, "해당 ID를 가진 좌석의 패널티 로그가 존재하지 않습니다."),
    // 공지사항 관련 에러
    NOTICE_NO_ID(400, "해당 ID를 가진 공지사항이 없습니다."),
    NOTICE_FAIL_HIT(500, "조회수 증가에 실패하였습니다.")
    ;

    private Integer status; // 에러 상태 코드
    private String message; // 에러 메세지

}
