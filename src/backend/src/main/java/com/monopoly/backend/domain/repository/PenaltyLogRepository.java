package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.PenaltyLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface PenaltyLogRepository extends JpaRepository<PenaltyLog, Long> {

    // [등록]
    PenaltyLog save(PenaltyLog penaltyLog);

    // [조회] 리스트 By 유저 ID
    Stream<PenaltyLog> findAllByUserId(Long userId);

    // [조회] 리스트 By 좌석 ID
    Stream<PenaltyLog> findAllBySeatId(Long seatId);

}
