package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.User;

public interface UserRepositoryCustom {

    // [수정] 해당 유저 ID의 유저를 관리자로 등록
    void update(User user);

    Long updateAccessToken(User user);

}
