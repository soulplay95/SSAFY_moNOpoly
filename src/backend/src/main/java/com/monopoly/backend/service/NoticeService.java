package com.monopoly.backend.service;

import com.monopoly.backend.domain.entity.Notice;
import com.monopoly.backend.dto.response.NoticeRes;
import com.monopoly.backend.dto.request.NoticeSaveReq;
import com.monopoly.backend.domain.repository.NoticeRepository;
import com.monopoly.backend.dto.request.NoticeUpdateReq;
import com.monopoly.backend.exception.ErrorCode;
import com.monopoly.backend.exception.NoNoticeIdException;
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
public class NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 등록
     * @param req
     * @return ID
     */
    public Long save(NoticeSaveReq req) {
        return noticeRepository.save(req.toEntity()).getId();
    }

    /**
     * ID로 조회
     * @param id
     * @return Response
     */
    public NoticeRes findById(Long id) {
        Notice entity = noticeRepository.findById(id)
                .orElseThrow(() -> new NoNoticeIdException(ErrorCode.NOTICE_NO_ID));

        return new NoticeRes(entity);
    }

    /**
     * 도서관 ID로 리스트 조회
     * @param libraryId
     * @return List
     */
    public List<NoticeRes> findAllDesc(Long libraryId) {
        return noticeRepository.findAllByLibraryId(libraryId)
                .map(NoticeRes::new)
                .collect(Collectors.toList());
    }

    /**
     * 제목 or 내용으로 검색
     * @param key
     * @param word
     * @return List
     */
    public List<NoticeRes> getListByTitleOrContent(String key, String word) {
        return noticeRepository.search(key, word)
                .map(NoticeRes::new)
                .collect(Collectors.toList());
    }

    /**
     * 조회수 1증가
     * @param id
     * @return Long
     */
    public Long updateHitCnt(Long id) {
        if (noticeRepository.updateHitCnt(id) == 1) {
            return 1L;
        }

        return -1L;
    }

    /**
     * 수정
     * @param req
     */
    public void update(NoticeUpdateReq req) {
        noticeRepository.update(req.toEntity());
    }

}
