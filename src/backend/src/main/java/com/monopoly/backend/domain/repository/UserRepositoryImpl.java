package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.QUser;
import com.monopoly.backend.domain.entity.User;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final EntityManager em;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    // [수정] 해당 유저 ID의 유저를 관리자로 등록
    @Override
    public void update(User user) {
        QUser qUser = QUser.user;
        JPAUpdateClause update = new JPAUpdateClause(em, qUser);

        update
                .set(qUser.type, 1)
                .where(qUser.id.eq(user.getId()))
                .execute();
    }

    // 엑세스토큰 수정
    @Override
    public Long updateAccessToken(User user) {
        QUser qUser = QUser.user;
        JPAUpdateClause update = new JPAUpdateClause(em, qUser);

        return update
                .set(qUser.accessToken, user.getAccessToken())
                .where(qUser.id.eq(user.getId()))
                .execute();
    }

}
