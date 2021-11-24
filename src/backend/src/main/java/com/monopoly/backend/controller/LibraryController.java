package com.monopoly.backend.controller;

import com.monopoly.backend.dto.response.LibraryRes;
import com.monopoly.backend.service.LibraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "도서관 API", tags = {"Library"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @ApiOperation(value = "[조회] 시도명 리스트", notes = "특별시, 광역시, 도 단위의 시도명 리스트를 조회한다.")
    @GetMapping("/sidos")
    public ResponseEntity<List<String>> getSidos() {
        return ResponseEntity.status(200).body(libraryService.findSidos());
    }

    @ApiOperation(value = "[조회] 구군명 리스트", notes = "시도명에 해당하는 구군 리스트를 조회한다.")
    @GetMapping("/guguns/{sido}")
    public ResponseEntity<List<String>> getGuguns(
            @ApiParam(value = "시도명", required = true) @PathVariable String sido) {
        return ResponseEntity.status(200).body(libraryService.findAllGugunBySido(sido));
    }

    @ApiOperation(value = "[조회] 도서관 리스트", notes = "구군명으로 도서관 리스트를 조회한다.")
    @GetMapping("/{gugun}")
    public ResponseEntity<List<LibraryRes>> getListByGugun(
            @ApiParam(value = "구군명", required = true)
            @PathVariable String gugun) {
        return ResponseEntity.status(200).body(libraryService.findAllByGugun(gugun));
    }

    @ApiOperation(value = "[조회] By 도서관명", notes = "도서관 이름으로 도서관을 조회한다.")
    @GetMapping("/search/{name}")
    public ResponseEntity<LibraryRes> getByName(
            @ApiParam(value = "도서관명", required = true)
            @PathVariable String name) {
        return ResponseEntity.status(200).body(libraryService.getByName(name));
    }

    @ApiOperation(value = "[조회] By ID", notes = "도서관 ID로 도서관을 조회한다.")
    @GetMapping("/search")
    public ResponseEntity<LibraryRes> getById(
            @ApiParam(value = "도서관 ID", required = true)
            @RequestParam(value = "id") Long id) {
        return ResponseEntity.status(200).body(libraryService.getById(id));
    }

}
