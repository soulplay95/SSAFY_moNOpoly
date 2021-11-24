package com.monopoly.backend.dto.request;

import com.monopoly.backend.domain.entity.Reservation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 예약 API - 등록 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[예약] 등록 요청")
public class ReservationSaveReq {

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long userId;

    @ApiModelProperty(value = "좌석 ID", example = "1")
    private Long seatId;

    @ApiModelProperty(value = "시작 시간", example = "2021-08-05 14:48:37")
    private String startTime;

    @ApiModelProperty(value = "종료 시간", example = "2021-08-05 14:48:37")
    private String endTime;

    @ApiModelProperty(value = "최대 설정 시간", example = "480")
    private Integer maxSetTime;

    @Builder
    public ReservationSaveReq(Long userId, Long seatId, String startTime, String endTime, Integer maxSetTime) {
        this.userId = userId;
        this.seatId = seatId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxSetTime = maxSetTime;
    }

    public Reservation toEntity() {
        return Reservation.builder()
                .userId(userId)
                .seatId(seatId)
                .startTime(toDateTime(startTime))
                .endTime(toDateTime(endTime))
                .maxSetTime(maxSetTime)
                .build();
    }

    private LocalDateTime toDateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(time, formatter);
    }

}
