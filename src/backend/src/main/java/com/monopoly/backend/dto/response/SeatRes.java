package com.monopoly.backend.dto.response;

import com.monopoly.backend.api.DateTimeToStringAPI;
import com.monopoly.backend.domain.entity.Seat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 좌석 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[좌석] 메인 응답")
public class SeatRes {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long userId;

    @ApiModelProperty(value = "섹션 ID", example = "1")
    private Long sectionId;

    @ApiModelProperty(value = "FE 상태", example = "0")
    private Integer frontState;

    @ApiModelProperty(value = "Detection 결과 상태", example = "0")
    private Integer camState;

    @ApiModelProperty(value = "x좌표", example = "1")
    private Integer coordinateX;

    @ApiModelProperty(value = "y좌표", example = "1")
    private Integer coordinateY;

    @ApiModelProperty(value = "FE x좌표", example = "1")
    private Integer feCoordinateX;

    @ApiModelProperty(value = "FE y좌표", example = "1")
    private Integer feCoordinateY;

    @ApiModelProperty(value = "최초 사석화 감지 시간", example = "2021-08-05 14:48:37")
    private String detectionTime;

    public SeatRes(Seat entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.sectionId = entity.getSectionId();
        this.frontState = entity.getFrontState();
        this.camState = entity.getCamState();
        this.coordinateX = entity.getCoordinateX();
        this.coordinateY = entity.getCoordinateY();
        this.feCoordinateX = entity.getFeCoordinateX();
        this.feCoordinateY = entity.getFeCoordinateY();
        this.detectionTime = DateTimeToStringAPI.dateTimeToString(entity.getDetectionTime());
    }

}
