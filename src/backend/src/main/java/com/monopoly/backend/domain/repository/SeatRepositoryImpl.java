package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.QSeat;
import com.monopoly.backend.domain.entity.Seat;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
public class SeatRepositoryImpl implements SeatRepositoryCustom {

    private final EntityManager em;

    public SeatRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    // [수정]
    @Override
    public Long updateFE(Seat seat) {
        QSeat qSeat = QSeat.seat;
        JPAUpdateClause update = new JPAUpdateClause(em, qSeat);

        return update
                .set(qSeat.frontState, seat.getFrontState())
                .set(qSeat.feCoordinateX, seat.getFeCoordinateX())
                .set(qSeat.feCoordinateY, seat.getFeCoordinateY())
                .set(qSeat.userId, seat.getUserId())
                .where(qSeat.id.eq(seat.getId()), qSeat.sectionId.eq(seat.getSectionId()))
                .execute();
    }

    @Override
    public Long updateDE(Seat seat) {
        QSeat qSeat = QSeat.seat;
        JPAUpdateClause update = new JPAUpdateClause(em, qSeat);

        return update
                .set(qSeat.camState, seat.getCamState())
                .set(qSeat.detectionTime, seat.getDetectionTime())
                .where(qSeat.id.eq(seat.getId()), qSeat.sectionId.eq(seat.getSectionId()))
                .execute();
    }

    @Override
    public Long update30(Seat seat) {
        QSeat qSeat = QSeat.seat;
        JPAUpdateClause update = new JPAUpdateClause(em, qSeat);

        return update
                .set(qSeat.camState, seat.getCamState())
                .set(qSeat.detectionTime, seat.getDetectionTime())
                .set(qSeat.frontState, 2)
                .where(qSeat.id.eq(seat.getId()), qSeat.sectionId.eq(seat.getSectionId()))
                .execute();
    }

    @Override
    public Long update60(Seat seat) {
        QSeat qSeat = QSeat.seat;
        JPAUpdateClause update = new JPAUpdateClause(em, qSeat);

        return update
                .set(qSeat.camState, 0)
                .set(qSeat.frontState, 0)
                .setNull(qSeat.userId)
                .setNull(qSeat.detectionTime)
                .where(qSeat.id.eq(seat.getId()), qSeat.sectionId.eq(seat.getSectionId()))
                .execute();
    }

}
