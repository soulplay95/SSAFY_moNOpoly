package com.monopoly.backend.controller;

import com.monopoly.backend.dto.request.ImageSaveReq;
import com.monopoly.backend.dto.response.ImageRes;
import com.monopoly.backend.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(description = "이미지 API", tags = {"Image"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @ApiOperation(value = "[등록]", notes = "이미지 정보를 받아 등록한다.")
    @PostMapping("/{type}")
    public ResponseEntity<ImageRes> save(
            @ApiParam(value = "게시판 타입(notice 또는 locker)", required = true)
            @PathVariable(value = "type") String type,
            @ApiParam(value = "게시판 ID", required = true)
            @RequestParam(value = "id") Long id,
            @ApiParam(value = "FormData", required = true)
            @RequestParam(value = "file") MultipartFile file) {

        return ResponseEntity.status(200).body(new ImageRes(imageService.save(type, id, file)));
    }

    @ApiOperation(value = "[조회] 리스트 By 게시판 타입, 게시판 ID", notes = "게시판 타입, ID를 받아 리스트를 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<List<ImageRes>> getListByBoardTypeAndBoardId(
            @ApiParam(value = "게시판 type, 게시판 ID", required = true)
            @RequestParam(value = "boardType") String boardType, @RequestParam(value = "boardId") Long boardId) {

        return ResponseEntity.status(200).body(imageService.getListByBoardTypeAndBoardId(boardType, boardId));
    }

}
