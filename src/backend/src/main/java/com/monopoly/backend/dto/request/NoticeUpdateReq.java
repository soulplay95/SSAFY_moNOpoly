package com.monopoly.backend.dto.request;

import com.monopoly.backend.domain.entity.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 공지사항 API - 수정 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[공지사항] 수정 요청")
public class NoticeUpdateReq {

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

    @Builder
    public NoticeUpdateReq(Long id, String title, String content, Long userId, Boolean isHead, Long hitCnt, Long libraryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.isHead = isHead;
        this.hitCnt = hitCnt;
        this.libraryId = libraryId;
    }

    public Notice toEntity() {
        return Notice.builder()
                .id(id)
                .title(title)
                .content(content)
                .isHead(isHead)
                .hitCnt(hitCnt)
                .userId(userId)
                .libraryId(libraryId)
                .build();
    }

}
