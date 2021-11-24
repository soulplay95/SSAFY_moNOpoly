package com.monopoly.backend.controller;

import com.monopoly.backend.dto.response.BaseResponseBody;
import com.monopoly.backend.dto.request.LockerUpdateReq;
import com.monopoly.backend.dto.response.LockerRes;
import com.monopoly.backend.dto.request.LockerSaveReq;
import com.monopoly.backend.service.LockerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(description = "물품보관함 API", tags = {"Locker"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/locker")
public class LockerController {

    private final LockerService lockerService;

    @ApiOperation(value = "[등록]", notes = "라커 정보를 받아 등록한다.")
    @PostMapping
    public ResponseEntity<LockerRes> save(
            @ApiParam(value = "도서관 ID", required = true)
            @RequestParam(value = "libraryId") Long libraryId,
            @ApiParam(value = "제목", required = true)
            @RequestParam(value = "title") String title,
            @ApiParam(value = "내용", required = true)
            @RequestParam(value = "content") String content,
            @ApiParam(value = "패널티로그 ID", required = true)
            @RequestParam(value = "penaltyLogId") Long penaltyLogId,
            @ApiParam(value = "습득일", required = true)
            @RequestParam(value = "foundDate") String foundDate,
            @ApiParam(value = "파일(FormData)", required = true)
            @RequestParam(value = "file") MultipartFile file) {

        return ResponseEntity.status(200).body(new LockerRes(lockerService.save(libraryId, title, content, penaltyLogId, foundDate, file)));
    }

    @ApiOperation(value = "[조회] 리스트 By 도서관 ID", notes = "도서관 ID로 물품보관함 리스트를 조회한다.")
    @GetMapping("/list/{libraryId}")
    public ResponseEntity<List<LockerRes>> getListByLibraryId(
            @ApiParam(value = "도서관 ID", required = true)
            @PathVariable Long libraryId) {

        return ResponseEntity.status(200).body(lockerService.getListByLibraryId(libraryId));
    }

    @ApiOperation(value = "[조회] 리스트 By 패널티 로그 ID", notes = "패널티 로그 ID로 물품보관함 리스트를 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<List<LockerRes>> getListBypenaltyLogId(
            @ApiParam(value = "패널티 로그 ID", required = true)
            @RequestParam(value = "penaltyLogId") Long penaltyLogId) {

        return ResponseEntity.status(200).body(lockerService.getListByPenaltyLogId(penaltyLogId));
    }

    @ApiOperation(value = "[삭제] By ID", notes = "물품보관함 ID로 삭제한다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<? extends BaseResponseBody> deleteById(
            @ApiParam(value = "물품보관함 ID", required = true)
            @PathVariable Long id) {

        lockerService.deleteById(id);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @ApiOperation(value = "[수정]", notes = "물품보관함을 수정한다.")
    @PutMapping
    public ResponseEntity<LockerRes> update(
            @ApiParam(value = "물품보관함", required = true)
            @RequestBody LockerUpdateReq locker) {

        lockerService.update(locker);

        return ResponseEntity.status(200).body(lockerService.getById(locker.getId()));
    }

    @ApiOperation(value = "[삭제] 만료일 넘긴 게시글 삭제", notes = "현재 날짜 이후의 만료일을 가진 게시글들을 삭제한다.")
    @DeleteMapping
    public ResponseEntity<Long> deleteById() {
        return ResponseEntity.status(200).body(lockerService.deleteAllByEx());
    }

}
