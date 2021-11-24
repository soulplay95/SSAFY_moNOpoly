package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Notice;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface NoticeRepositoryCustom {

    // [수정]
    void update(Notice notice);

    // [조회] 리스트 By 제목, 내용
    Stream<Notice> search(@Param("key") String key, @Param("word") String word);

}
