package com.monopoly.backend.dto.response;

import com.monopoly.backend.api.DateTimeToStringAPI;
import com.monopoly.backend.domain.entity.Reservation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 예약 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[예약] 메인 응답")
public class ReservationRes {

    @ApiModelProperty(value = "예약 ID", example = "1")
    private Long id;

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

    public ReservationRes(Reservation entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.seatId = entity.getSeatId();
        this.startTime = DateTimeToStringAPI.dateTimeToString(entity.getStartTime());
        this.endTime = DateTimeToStringAPI.dateTimeToString(entity.getEndTime());
        this.maxSetTime = entity.getMaxSetTime();
    }

}
