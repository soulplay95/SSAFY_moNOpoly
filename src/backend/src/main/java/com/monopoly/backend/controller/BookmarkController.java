package com.monopoly.backend.controller;

import com.monopoly.backend.dto.response.BaseResponseBody;
import com.monopoly.backend.dto.response.BookmarkRes;
import com.monopoly.backend.dto.request.BookmarkSaveReq;
import com.monopoly.backend.service.BookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "즐겨찾기 API", tags = {"Bookmark"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @ApiOperation(value = "[등록]", notes = "즐겨찾기 등록")
    @PostMapping
    public ResponseEntity<BookmarkRes> saveBookmark(
            @ApiParam(value = "즐겨찾기 정보", required = true)
            @RequestBody BookmarkSaveReq bookmark) {

        return ResponseEntity.status(200).body(new BookmarkRes(bookmarkService.save(bookmark)));
    }

    @ApiOperation(value = "[조회] 리스트 By 유저 ID", notes = "유저 ID로 즐겨찾기 리스트를 조회한다.")
    @GetMapping("/{userId}")
    public ResponseEntity<List<BookmarkRes>> getBookmarkByUserId(
            @ApiParam(value = "유저 ID", required = true)
            @RequestParam(value = "userId", required = true) Long userId) {

        return ResponseEntity.status(200).body(bookmarkService.findAllByUserId(userId));
    }

    @ApiOperation(value = "[삭제] By ID", notes = "즐겨찾기 ID로 삭제")
    @DeleteMapping("/{id}")
    private ResponseEntity<? extends BaseResponseBody> deleteBookmark(
            @ApiParam(value = "즐겨찾기 ID", required = true)
            @PathVariable Long id) {

        bookmarkService.deleteById(id);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "OK"));
    }

}
