package com.monopoly.backend.dto.request;

import com.monopoly.backend.domain.entity.Admin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 관리자 API - 등록 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[관리자] 등록 요청")
public class AdminSaveReq {

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long id;

    @ApiModelProperty(value = "도서관 ID", example = "1")
    private Long libraryId;

    @Builder
    public AdminSaveReq(Long id, Long libraryId) {
        this.id = id;
        this.libraryId = libraryId;
    }

    public Admin toEntity() {
        return Admin.builder()
                .id(id)
                .libraryId(libraryId)
                .build();
    }

}
