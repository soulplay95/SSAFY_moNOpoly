package com.monopoly.backend.dto.request;

import com.monopoly.backend.domain.entity.Seat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 좌석 API - 등록 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[좌석] 등록 요청")
public class SeatSaveReq {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "섹션 ID", example = "1")
    private Long sectionId;

    @ApiModelProperty(value = "x좌표", example = "1")
    private Integer coordinateX;

    @ApiModelProperty(value = "y좌표", example = "1")
    private Integer coordinateY;

    @Builder
    public SeatSaveReq(Long id, Long sectionId, Integer coordinateX, Integer coordinateY) {
        this.id = id;
        this.sectionId = sectionId;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Seat toEntity() {
        return Seat.builder()
                .id(id)
                .sectionId(sectionId)
                .coordinateX(coordinateX)
                .coordinateY(coordinateY)
                .frontState(0)
                .camState(0)
                .build();
    }

}
