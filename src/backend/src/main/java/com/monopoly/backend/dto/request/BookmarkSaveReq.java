package com.monopoly.backend.dto.request;

import com.monopoly.backend.domain.entity.Bookmark;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 즐겨찾기 API - 등록 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[즐겨찾기] 등록 요청")
public class BookmarkSaveReq {

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long userId;

    @ApiModelProperty(value = "도서관 ID", example = "1")
    private Long libraryId;

    @Builder
    public BookmarkSaveReq(Long userId, Long libraryId) {
        this.userId = userId;
        this.libraryId = libraryId;
    }

    public Bookmark toEntity() {
        return Bookmark.builder()
                .userId(userId)
                .libraryId(libraryId)
                .build();
    }

}
