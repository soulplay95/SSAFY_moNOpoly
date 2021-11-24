package com.monopoly.backend.dto.response;

import com.monopoly.backend.api.DateTimeToStringAPI;
import com.monopoly.backend.domain.entity.Bookmark;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 즐겨찾기 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[즐겨찾기] 메인 응답")
public class BookmarkRes {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long userId;

    @ApiModelProperty(value = "도서관 ID", example = "1")
    private Long libraryId;

    @ApiModelProperty(value = "생성 시간", example = "2021-08-05 14:48:37")
    private String createdDate;

    @ApiModelProperty(value = "수정 시간", example = "2021-08-05 14:48:37")
    private String modifiedDate;

    public BookmarkRes(Bookmark entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.libraryId = entity.getLibraryId();
        this.createdDate = DateTimeToStringAPI.dateTimeToString(entity.getCreatedDate());
        this.modifiedDate = DateTimeToStringAPI.dateTimeToString(entity.getModifiedDate());
    }

}
