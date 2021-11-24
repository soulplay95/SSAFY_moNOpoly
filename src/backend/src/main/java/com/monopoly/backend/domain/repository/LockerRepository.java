package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface LockerRepository extends JpaRepository<Locker, Long>, LockerRepositoryCustom {

    // [등록]
    Locker save(Locker locker);

    // [삭제] By ID
    void deleteById(Long id);

    // [조회] 리스트 By 도서관 ID
    Stream<Locker> findAllByLibraryId(Long libraryId);

    // [조회] 리스트 By 패널티 로그 ID
    Stream<Locker> findAllByPenaltyLogId(Long penaltyLogId);

    // [조회] By ID
    Optional<Locker> findById(Long id);

}
