package com.monopoly.backend.dto.request;

import com.monopoly.backend.api.StringToDateTimeAPI;
import com.monopoly.backend.domain.entity.Locker;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 물품보관함 API - 생성 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[물품보관함] 생성 요청")
public class LockerSaveReq {

    @ApiModelProperty(value = "패널티로그 ID", example = "1")
    private Long penaltyLogId;

    @ApiModelProperty(value = "도서관 ID", example = "1")
    private Long libraryId;

    @ApiModelProperty(value = "제목", example = "찾아가세요")
    private String title;

    @ApiModelProperty(value = "내용", example = "빨리요")
    private String content;

    @ApiModelProperty(value = "습득일", example = "2021-08-05 14:48:37")
    private String foundDate;

    @Builder
    public LockerSaveReq(Long penaltyLogId, Long libraryId, String title, String content, String foundDate) {
        this.penaltyLogId = penaltyLogId;
        this.libraryId = libraryId;
        this.title = title;
        this.content = content;
        this.foundDate = foundDate;
    }

    public Locker toEntity() {
        return Locker.builder()
                .penaltyLogId(penaltyLogId)
                .libraryId(libraryId)
                .title(title)
                .content(content)
                .expireDate(LocalDateTime.now().plusDays(15))
                .foundDate(StringToDateTimeAPI.stringToDateTime(foundDate))
                .build();
    }

}
