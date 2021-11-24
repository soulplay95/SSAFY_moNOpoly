package com.monopoly.backend.dto.request;

import com.monopoly.backend.domain.entity.PenaltyLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 패널티 로그 API - 등록 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[패널티 로그] 등록 요청")
public class PenaltyLogSaveReq {

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long userId;

    @ApiModelProperty(value = "좌석 ID", example = "1")
    private Long seatId;

    @ApiModelProperty(value = "섹션 ID", example = "1")
    private Long sectionId;

    @Builder
    public PenaltyLogSaveReq(Long userId, Long seatId, Long sectionId) {
        this.userId = userId;
        this.seatId = seatId;
        this.sectionId = sectionId;
    }

    public PenaltyLog toEntity() {
        return PenaltyLog.builder()
                .userId(userId)
                .seatId(seatId)
                .sectionId(sectionId)
                .build();
    }

}
