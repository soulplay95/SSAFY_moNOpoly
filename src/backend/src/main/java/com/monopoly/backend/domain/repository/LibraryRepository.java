package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.stream.Stream;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    // [조회] 리스트 시도명 목록 가져오기
    @Query("SELECT distinct l.sido FROM Library l")
    Stream<String> findSidos();

    // [조회] 리스트 시도명으로 구군명 목록 가져오기
    @Query("SELECT distinct l.gugun FROM Library l WHERE l.sido=:sido")
    Stream<String> findAllGugunBySido(@Param("sido") String sido);

    // [조회] 리스트 By 구군명
    Stream<Library> findAllByGugun(String gugun);

    // [조회] By 도서관이름
    Optional<Library> findByName(String name);

    // [조회] By ID
    Optional<Library> findById(Long id);

}
