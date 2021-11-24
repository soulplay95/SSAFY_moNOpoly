package com.monopoly.backend.dto.response;

import com.monopoly.backend.domain.entity.Image;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 이미지 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[이미지] 메인 응답")
public class ImageRes {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "게시판 타입", example = "notice")
    private String boardType;

    @ApiModelProperty(value = "게시판 ID", example = "1")
    private Long boardId;

    @ApiModelProperty(value = "img", example = "blob")
    private Byte[] img;

    public ImageRes(Image entity) {
        this.id = entity.getId();
        this.boardType = entity.getBoardType();
        this.boardId = entity.getBoardId();
        this.img = entity.getImg();
    }

}
