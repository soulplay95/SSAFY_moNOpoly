package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Notice;
import com.monopoly.backend.domain.entity.QNotice;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.stream.Stream;

@Transactional
public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final EntityManager em;

    public NoticeRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    // [수정]
    @Override
    public void update(Notice notice) {
        QNotice qNotice = QNotice.notice;
        JPAUpdateClause update = new JPAUpdateClause(em, qNotice);

        update
                .set(qNotice.title, notice.getTitle())
                .set(qNotice.content, notice.getContent())
                .set(qNotice.isHead, notice.getIsHead())
                .set(qNotice.hitCnt, notice.getHitCnt())
                .where(qNotice.id.eq(notice.getId()))
                .execute();
    }

    @Override
    public Stream<Notice> search(String key, String word) {
        QNotice qNotice = QNotice.notice;
        JPAQuery query = new JPAQuery(em);

        if (word == null) {
            query.select(qNotice)
                    .from(qNotice)
                    .orderBy(qNotice.modifiedDate.desc());
        } else {
            if (key.equals("all")) {
                query.select(qNotice)
                        .from(qNotice)
                        .where(qNotice.title.like("%" + word + "%").or(qNotice.content.like(("%" + word + "%"))))
                        .orderBy(qNotice.modifiedDate.desc());
            } else if (key.equals("title")) {
                query.select(qNotice)
                        .from(qNotice)
                        .where(qNotice.title.like("%" + word + "%"))
                        .orderBy(qNotice.modifiedDate.desc());
            } else if (key.equals("content")) {
                query.select(qNotice)
                        .from(qNotice)
                        .where(qNotice.content.like("%" + word + "%"))
                        .orderBy(qNotice.modifiedDate.desc());
            }
        }

        return query.fetch().stream();
    }

}
