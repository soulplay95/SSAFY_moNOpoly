package com.monopoly.backend.dto.response;

import com.monopoly.backend.api.DateTimeToStringAPI;
import com.monopoly.backend.domain.entity.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 공지사항 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[공지사항] 메인 응답")
public class NoticeRes {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long userId;

    @ApiModelProperty(value = "도서관 ID", example = "1")
    private Long libraryId;

    @ApiModelProperty(value = "제목", example = "공지1")
    private String title;

    @ApiModelProperty(value = "내용", example = "하이")
    private String content;

    @ApiModelProperty(value = "헤드 공지", example = "true")
    private Boolean isHead;

    @ApiModelProperty(value = "조회수", example = "1")
    private Long hitCnt;

    @ApiModelProperty(value = "생성 시간", example = "2021-08-05 14:48:37")
    private String createdDate;

    @ApiModelProperty(value = "수정 시간", example = "2021-08-05 14:48:37")
    private String modifiedDate;

    public NoticeRes(Notice entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.libraryId = entity.getLibraryId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.isHead = entity.getIsHead();
        this.hitCnt = entity.getHitCnt();
        this.createdDate = DateTimeToStringAPI.dateTimeToString(entity.getCreatedDate());
        this.modifiedDate = DateTimeToStringAPI.dateTimeToString(entity.getModifiedDate());
    }

}
