package com.monopoly.backend.dto.response;

import com.monopoly.backend.api.DateTimeToStringAPI;
import com.monopoly.backend.domain.entity.Locker;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

/**
 * 물품보관함 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[물품보관함] 메인 응답")
public class LockerRes {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "패널티로그 ID", example = "1")
    private Long penaltyLogId;

    @ApiModelProperty(value = "도서관 ID", example = "1")
    private Long libraryId;

    @ApiModelProperty(value = "제목", example = "찾아가세요")
    private String title;

    @ApiModelProperty(value = "내용", example = "빨리요")
    private String content;

    @ApiModelProperty(value = "만료일", example = "2021-08-05 14:48:37")
    private String expireDate;

    @ApiModelProperty(value = "습득일", example = "2021-08-05 14:48:37")
    private String foundDate;

    @ApiModelProperty(value = "생성일", example = "2021-08-05 14:48:37")
    private String createdDate;

    @ApiModelProperty(value = "수정일", example = "2021-08-05 14:48:37")
    private String modifiedDate;

    @ApiModelProperty(value = "이미지 Blob", example = "A")
    private Byte[] image;

    public LockerRes(Locker entity) {
        this.id = entity.getId();
        this.penaltyLogId = entity.getPenaltyLogId();
        this.libraryId = entity.getLibraryId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.expireDate = DateTimeToStringAPI.dateTimeToString(entity.getExpireDate());
        this.foundDate = DateTimeToStringAPI.dateTimeToString(entity.getFoundDate());
        this.createdDate = DateTimeToStringAPI.dateTimeToString(entity.getCreatedDate());
        this.modifiedDate = DateTimeToStringAPI.dateTimeToString(entity.getModifiedDate());
        this.image = entity.getImage();
    }

}
