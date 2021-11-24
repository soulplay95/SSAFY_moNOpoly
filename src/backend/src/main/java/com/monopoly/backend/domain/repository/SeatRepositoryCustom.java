package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Seat;

public interface SeatRepositoryCustom {

    // [수정]
    Long updateFE(Seat seat);

    Long updateDE(Seat seat);

    Long update30(Seat seat);

    Long update60(Seat seat);

}
