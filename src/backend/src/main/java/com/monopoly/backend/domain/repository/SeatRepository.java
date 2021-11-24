package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.stream.Stream;

public interface SeatRepository extends JpaRepository<Seat, Long>, SeatRepositoryCustom {

    // [등록]
    Seat save(Seat seat);

    // [조회] By ID
    Optional<Seat> findByIdAndSectionId(Long id, Long sectionId);

    // [조회] 리스트 By 섹션 ID
    @Query("SELECT s FROM Seat s WHERE s.sectionId=:sectionId")
    Stream<Seat> findAllBySectionId(@Param(value = "sectionId") Long sectionId);

    // [조회] By 유저 ID
    Optional<Seat> findByUserId(Long userId);

}
