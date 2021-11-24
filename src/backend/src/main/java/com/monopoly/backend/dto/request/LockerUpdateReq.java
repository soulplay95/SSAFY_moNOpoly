package com.monopoly.backend.dto.request;

import com.monopoly.backend.api.StringToDateTimeAPI;
import com.monopoly.backend.domain.entity.Locker;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 물품보관함 API - 수정 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[물품보관함] 수정 요청")
public class LockerUpdateReq {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "제목", example = "찾아가세요")
    private String title;

    @ApiModelProperty(value = "내용", example = "빨리요")
    private String content;

    @ApiModelProperty(value = "습득일", example = "2021-08-05 14:48:37")
    private String foundDate;

    @Builder
    public LockerUpdateReq(Long id, String title, String content, String foundDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.foundDate = foundDate;
    }

    public Locker toEntity() {
        return Locker.builder()
                .id(id)
                .title(title)
                .content(content)
                .foundDate(StringToDateTimeAPI.stringToDateTime(foundDate))
                .build();
    }

}
