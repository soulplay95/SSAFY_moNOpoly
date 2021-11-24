package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // [등록]
    Reservation save(Reservation reservation);

    // [삭제] By ID
    void deleteById(Long id);

    // [조회] By 좌석 ID
    Optional<Reservation> findBySeatId(Long seatId);

}
