package com.monopoly.backend.dto.response;

import com.monopoly.backend.domain.entity.Library;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 도서관 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[도서관] 메인 응답")
public class LibraryRes {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "도서관명", example = "파란만장 도서관")
    private String name;

    @ApiModelProperty(value = "시도명", example = "경기도")
    private String sido;

    @ApiModelProperty(value = "구군명", example = "화성시")
    private String gugun;

    @ApiModelProperty(value = "총좌석수", example = "142")
    private String seatCounts;

    @ApiModelProperty(value = "주소", example = "경기도 화성시")
    private String address;

    @ApiModelProperty(value = "연락처", example = "032-465-3853")
    private String phone;

    @ApiModelProperty(value = "url", example = "https://www.example.com")
    private String url;

    public LibraryRes(Library entity) {
        id = entity.getId();
        name = entity.getName();
        sido = entity.getSido();
        gugun = entity.getGugun();
        seatCounts = entity.getSeatCounts();
        address = entity.getAddress();
        phone = entity.getPhone();
        url = entity.getUrl();
    }

}
