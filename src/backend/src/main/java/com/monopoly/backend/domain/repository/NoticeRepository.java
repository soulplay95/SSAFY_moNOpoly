package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {

    // [등록]
    Notice save(Notice notice);

    // [삭제] By ID
    void deleteById(Long id);

    // [조회] 리스트 By 도서관 ID
    Stream<Notice> findAllByLibraryId(Long libraryId);

    // [수정] 조회수 1 증가
    @Modifying
    @Query("update Notice n set n.hitCnt = n.hitCnt + 1 where n.id = :id")
    Integer updateHitCnt(@Param("id") Long id);

}
