package com.monopoly.backend.dto.response;

import com.monopoly.backend.domain.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 유저 API - 메인 응답
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[유저] 메인 응답")
public class UserRes {

    @ApiModelProperty(value = "유저 ID", example = "1834697173")
    private Long id;

    @ApiModelProperty(value = "유저 닉네임", example = "도명")
    private String nickname;

    @ApiModelProperty(value = "유저 이메일", example = "example@naver.com")
    private String email;

    @ApiModelProperty(value = "유저 연락처", example = "+82 10-1234-1234")
    private String phone;

    @ApiModelProperty(value = "유저 성별", example = "male")
    private String gender;

    @ApiModelProperty(value = "유저 타입", example = "0")
    private Integer type;

    public UserRes(User entity) {
        this.id = entity.getId();
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.gender = entity.getGender();
        this.type = entity.getType();
    }

}
