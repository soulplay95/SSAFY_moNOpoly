package com.monopoly.backend.dto.request;

import com.monopoly.backend.domain.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 유저 API - 회원가입 요청
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("[유저] 회원가입 요청")
public class UserSaveReq {

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

    @ApiModelProperty(value = "엑세스 토큰", example = "asvasvwqd1q56w156")
    private String accessToken;

    @Builder
    public UserSaveReq(Long id, String nickname, String email, String phone, String gender, Integer type, String accessToken) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.type = type;
        this.accessToken = accessToken;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .nickname(nickname)
                .email(email)
                .phone(phone)
                .gender(gender)
                .type(type)
                .accessToken(accessToken)
                .build();
    }

}
