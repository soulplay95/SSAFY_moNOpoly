package com.monopoly.backend.service;

import com.monopoly.backend.domain.entity.Bookmark;
import com.monopoly.backend.dto.response.BookmarkRes;
import com.monopoly.backend.dto.request.BookmarkSaveReq;
import com.monopoly.backend.domain.repository.BookmarkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    /**
     * 등록
     * @param dto
     * @return id
     */
    public Bookmark save(BookmarkSaveReq dto) {
        return bookmarkRepository.save(dto.toEntity());
    }

    /**
     * 유저 ID로 패널티 즐겨찾기 목록을 조회한다.
     * @param userId
     * @return List
     */
    public List<BookmarkRes> findAllByUserId(Long userId) {
        return bookmarkRepository.findAllByUserId(userId)
                .map(BookmarkRes::new)
                .collect(Collectors.toList());
    }

    /**
     * 예약 삭제 - 예약 ID로
     */
    public void deleteById(Long id) {
        bookmarkRepository.deleteById(id);
    }

}
