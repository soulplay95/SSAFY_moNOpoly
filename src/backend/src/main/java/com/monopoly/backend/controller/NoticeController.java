package com.monopoly.backend.controller;

import com.monopoly.backend.dto.response.BaseResponseBody;
import com.monopoly.backend.dto.response.NoticeRes;
import com.monopoly.backend.dto.request.NoticeSaveReq;
import com.monopoly.backend.dto.request.NoticeUpdateReq;
import com.monopoly.backend.exception.ErrorCode;
import com.monopoly.backend.exception.FailNoticeHitException;
import com.monopoly.backend.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "공지사항 API", tags = {"Notice"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @ApiOperation(value = "[등록]", notes = "공지사항을 등록한다.")
    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> save(
            @ApiParam(value = "공지사항 정보", required = true)
            @RequestBody NoticeSaveReq notice) {
        noticeService.save(notice);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "OK"));
    }

    @ApiOperation(value = "[조회] By ID", notes = "공지사항 ID로 조회한다.")
    @GetMapping("/{id}")
    public ResponseEntity<NoticeRes> getById(
            @ApiParam(value = "공지사항 ID", required = true)
            @PathVariable Long id) {

        return ResponseEntity.status(200).body(noticeService.findById(id));
    }

    @ApiOperation(value = "[조회] 리스트 By 도서관 ID", notes = "도서관 ID로 공지사항 리스트를 조회한다.")
    @GetMapping("/list/{libraryId}")
    public ResponseEntity<List<NoticeRes>> getListById(
            @ApiParam(value = "도서관 ID", required = true)
            @PathVariable Long libraryId) {

        return ResponseEntity.status(200).body(noticeService.findAllDesc(libraryId));
    }

    @ApiOperation(value = "[조회] 리스트 By 제목, 내용", notes = "제목 or 내용 or 제목+내용으로 공지사항 리스트를 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<List<NoticeRes>> getListByTitleOrContent(
            @ApiParam(value = "key: title or content or all", required = true)
            @RequestParam(value = "key") String key,
            @ApiParam(value = "검색어(word)")
            @RequestParam(value = "word", required = false) String word) {

        return ResponseEntity.status(200).body(noticeService.getListByTitleOrContent(key, word));
    }

    @ApiOperation(value = "[수정] 조회수 1 증가", notes = "공지사항 ID를 받아 조회수를 1 증가시킨다.")
    @PutMapping("/{id}")
    public ResponseEntity<NoticeRes> updateHitCnt(
            @ApiParam(value = "공지사항 ID", required = true)
            @PathVariable Long id) {

        if (noticeService.updateHitCnt(id) == -1L) {
            throw new FailNoticeHitException(ErrorCode.NOTICE_FAIL_HIT);
        }

        return ResponseEntity.status(200).body(noticeService.findById(id));
    }

    @ApiOperation(value = "[수정]", notes = "공지사항을 수정한다.")
    @PutMapping
    public ResponseEntity<NoticeRes> update(
            @ApiParam(value = "공지사항", required = true)
            @RequestBody NoticeUpdateReq notice) {

        noticeService.update(notice);

        return ResponseEntity.status(200).body(noticeService.findById(notice.getId()));
    }

}
