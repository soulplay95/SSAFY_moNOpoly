package com.monopoly.backend.dto.response;

import com.monopoly.backend.domain.entity.Admin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 관리자 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[관리자] 메인 응답")
public class AdminRes {

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long id;

    @ApiModelProperty(value = "도서관 ID", example = "1")
    private Long libraryId;

    public AdminRes(Admin entity) {
        this.id = entity.getId();
        this.libraryId = entity.getLibraryId();
    }

}
