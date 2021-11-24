package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Locker;
import com.monopoly.backend.domain.entity.QLocker;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Transactional
public class LockerRepositoryImpl implements LockerRepositoryCustom {

    private final EntityManager em;

    public LockerRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    // [수정]
    @Override
    public void update(Locker locker) {
        QLocker qLocker = QLocker.locker;
        JPAUpdateClause update = new JPAUpdateClause(em, qLocker);

        update
                .set(qLocker.title, locker.getTitle())
                .set(qLocker.content, locker.getContent())
                .set(qLocker.foundDate, locker.getFoundDate())
                .where(qLocker.id.eq(locker.getId()))
                .execute();
    }

    @Override
    public Long deleteAllByEx() {
        QLocker qLocker = QLocker.locker;
        JPADeleteClause delete = new JPADeleteClause(em, qLocker);

        return delete
                .where(qLocker.expireDate.after(LocalDateTime.now()))
                .execute();
    }

}
