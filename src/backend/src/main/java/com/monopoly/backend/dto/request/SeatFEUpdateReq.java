package com.monopoly.backend.dto.request;

import com.monopoly.backend.domain.entity.Seat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 좌석 API - 업데이트 요청(from FE)
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[좌석] 수정 요청 - FE")
public class SeatFEUpdateReq {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "섹션 ID", example = "1")
    private Long sectionId;

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long userId;

    @ApiModelProperty(value = "FE 상태", example = "0")
    private Integer frontState;

    @ApiModelProperty(value = "FE x좌표", example = "1")
    private Integer feCoordinateX;

    @ApiModelProperty(value = "FE y좌표", example = "1")
    private Integer feCoordinateY;

    @Builder
    public SeatFEUpdateReq(Long id, Long sectionId, Long userId, Integer frontState, Integer feCoordinateX, Integer feCoordinateY) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.frontState = frontState;
        this.feCoordinateX = feCoordinateX;
        this.feCoordinateY = feCoordinateY;
    }

    public Seat toEntity() {
        return Seat.builder()
                .id(id)
                .sectionId(sectionId)
                .userId(userId)
                .frontState(frontState)
                .feCoordinateX(feCoordinateX)
                .feCoordinateY(feCoordinateY)
                .build();
    }

}
