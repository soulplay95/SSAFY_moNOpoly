package com.monopoly.backend.domain.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 유저 entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "type")
    private Integer type;

    @Column(name = "access_token")
    private String accessToken;

    @Builder
    public User(Long id, String nickname, String email, String phone, String gender, Integer type, String accessToken) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.type = type;
        this.accessToken = accessToken;
    }

}
