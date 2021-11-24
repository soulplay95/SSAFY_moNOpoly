package com.monopoly.backend.dto.request;

import com.monopoly.backend.domain.entity.Image;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 이미지 API - 등록 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[이미지] 등록 요청")
public class ImageSaveReq {

    @ApiModelProperty(value = "게시판 타입", example = "notice")
    private String boardType;

    @ApiModelProperty(value = "게시판 ID", example = "1")
    private Long boardId;

    @ApiModelProperty(value = "img", example = "FORMDATA")
    private Byte[] img;

    @Builder
    public ImageSaveReq(String boardType, Long boardId, Byte[] img) {
        this.boardType = boardType;
        this.boardId = boardId;
        this.img = img;
    }

    public Image toEntity() {
        return Image.builder()
                .boardType(boardType)
                .boardId(boardId)
                .img(img)
                .build();
    }

}
