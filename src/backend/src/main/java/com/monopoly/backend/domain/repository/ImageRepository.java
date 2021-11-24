package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface ImageRepository extends JpaRepository<Image, Long> {

    // [등록]
    Image save(Image image);

    // [조회] 리스트 By 게시판 타입, 게시판 ID
    Stream<Image> findAllByBoardIdAndBoardType(String boardType, Long boardId);

}
