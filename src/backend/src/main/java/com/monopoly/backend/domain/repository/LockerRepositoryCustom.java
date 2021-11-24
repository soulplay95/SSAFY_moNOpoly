package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Locker;

public interface LockerRepositoryCustom {

    // [수정]
    void update(Locker locker);

    // [삭제] 만료일 끝난 게시글 삭제
    Long deleteAllByEx();

}
