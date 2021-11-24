package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    // [등록]
    Bookmark save(Bookmark bookmark);

    // [조회] 리스트 By 유저 ID
    Stream<Bookmark> findAllByUserId(Long userId);

    // [삭제] By ID
    void deleteById(Long id);

}
