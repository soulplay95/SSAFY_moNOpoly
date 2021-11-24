package com.monopoly.backend.controller;

import com.monopoly.backend.dto.request.AdminSaveReq;
import com.monopoly.backend.dto.response.AdminRes;
import com.monopoly.backend.dto.response.BaseResponseBody;
import com.monopoly.backend.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "도서관 관리자 API", tags = {"Admin"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @ApiOperation(value = "[등록]", notes = "관리자 정보를 받아 등록한다.")
    @PostMapping
    public ResponseEntity<AdminRes> save(
            @ApiParam(value = "관리자 정보", required = true)
            @RequestBody AdminSaveReq admin) {

        return ResponseEntity.status(200).body(new AdminRes(adminService.save(admin)));
    }

    @ApiOperation(value = "[조회] 리스트 By 도서관 ID", notes = "도서관 ID로 관리자 리스트를 조회한다.")
    @GetMapping("/{libraryId}")
    public ResponseEntity<List<AdminRes>> getListByLibraryId(
            @ApiParam(value = "도서관 ID", required = true)
            @PathVariable Long libraryId) {

        return ResponseEntity.status(200).body(adminService.getListByLibraryId(libraryId));
    }

    @ApiOperation(value = "관리자 삭제 By ID", notes = "관리자 ID로 관리자 테이블에서 삭제한다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<? extends BaseResponseBody> deleteById(
            @ApiParam(value = "관리자 ID", required = true)
            @PathVariable Long id) {

        adminService.deleteById(id);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

}
