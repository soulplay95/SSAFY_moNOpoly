package com.monopoly.backend.dto.response;

import com.monopoly.backend.api.DateTimeToStringAPI;
import com.monopoly.backend.domain.entity.PenaltyLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 패널티 로그 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[패널티 로그] 메인 응답")
public class PenaltyLogRes {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long userId;

    @ApiModelProperty(value = "좌석 ID", example = "1")
    private Long seatId;

    @ApiModelProperty(value = "섹션 ID", example = "1")
    private Long sectionId;

    @ApiModelProperty(value = "생성 시간", example = "2021-08-05 14:48:37")
    private String createdDate;

    public PenaltyLogRes(PenaltyLog entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.seatId = entity.getSeatId();
        this.sectionId = entity.getSectionId();
        this.createdDate = DateTimeToStringAPI.dateTimeToString(entity.getCreatedDate());
    }

}
