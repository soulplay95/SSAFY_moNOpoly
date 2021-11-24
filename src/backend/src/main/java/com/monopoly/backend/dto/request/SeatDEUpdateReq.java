package com.monopoly.backend.dto.request;

import com.monopoly.backend.api.StringToDateTimeAPI;
import com.monopoly.backend.domain.entity.Seat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 좌석 API - 업데이트 요청(from Detection Server)
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[좌석] 수정 요청 - Detection")
public class SeatDEUpdateReq {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "섹션 ID", example = "1")
    private Long sectionId;

    @ApiModelProperty(value = "Detection 결과 상태", example = "0")
    private Integer camState;

    @ApiModelProperty(value = "최초 사석화 감지 시간", example = "2021-08-05 14:48:37")
    private String detectionTime;

    @Builder
    public SeatDEUpdateReq(Long id, Long sectionId, Integer camState, String detectionTime) {
        this.id = id;
        this.sectionId = sectionId;
        this.camState = camState;
        this.detectionTime = detectionTime;
    }

    public Seat toEntity() {
        return Seat.builder()
                .id(id)
                .sectionId(sectionId)
                .camState(camState)
                .detectionTime(StringToDateTimeAPI.stringToDateTime(detectionTime))
                .build();
    }

}
