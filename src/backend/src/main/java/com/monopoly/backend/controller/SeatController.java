package com.monopoly.backend.controller;

import com.monopoly.backend.domain.entity.Seat;
import com.monopoly.backend.dto.request.SeatDEUpdateReq;
import com.monopoly.backend.dto.request.SeatSaveReq;
import com.monopoly.backend.dto.request.SeatFEUpdateReq;
import com.monopoly.backend.dto.response.BaseResponseBody;
import com.monopoly.backend.dto.response.SeatRes;
import com.monopoly.backend.exception.ErrorCode;
import com.monopoly.backend.exception.NoSeatByUserIdException;
import com.monopoly.backend.exception.NoSeatIdException;
import com.monopoly.backend.service.SeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "좌석 API", tags = {"Seat"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    @ApiOperation(value = "[등록]", notes = "좌석정보를 받아 등록한다.")
    @PostMapping
    public ResponseEntity<SeatRes> save(
            @ApiParam(value = "좌석 정보", required = true)
            @RequestBody SeatSaveReq seat) {

        return ResponseEntity.status(200).body(new SeatRes(seatService.save(seat)));
    }

    @ApiOperation(value = "[조회] By ID", notes = "좌석 ID, 섹션 ID로 조회한다.")
    @GetMapping("/{id}/{sectionId}")
    public ResponseEntity<SeatRes> findById(
            @ApiParam(value = "좌석 ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "섹션 ID", required = true)
            @PathVariable Long sectionId) {

        SeatRes result = seatService.findById(id, sectionId);
        if (result == null) {
            throw new NoSeatIdException(ErrorCode.SEAT_NO_ID);
        }

        return ResponseEntity.status(200).body(result);
    }

    @ApiOperation(value = "[조회] By 유저 ID", notes = "유저 ID로 조회한다.")
    @GetMapping
    public ResponseEntity<SeatRes> findByUserId(
            @ApiParam(value = "유저 ID", required = true)
            @RequestParam Long userId) {

        SeatRes result = seatService.findByUserId(userId);
        System.out.println(result);
        if (result == null) {
            throw new NoSeatByUserIdException(ErrorCode.SEAT_NO_USER_ID);
        }

        return ResponseEntity.status(200).body(result);
    }

    @ApiOperation(value = "[조회] 리스트 By 섹션 ID", notes = "Section ID로 좌석 리스트를 조회한다.")
    @GetMapping("/list/{sectionId}")
    public ResponseEntity<List<SeatRes>> findAllBySectionId(
            @ApiParam(value = "섹션 ID", required = true)
            @PathVariable Long sectionId) {

        return ResponseEntity.status(200).body(seatService.findAllBySectionId(sectionId));
    }

    @ApiOperation(value = "[수정] FE", notes = "FE단에서 좌석 정보를 수정한다.")
    @PutMapping("/fe")
    public ResponseEntity<? extends BaseResponseBody> updateFe(
            @ApiParam(value = "좌석 정보", required = true)
            @RequestBody SeatFEUpdateReq seat) {

        seatService.updateFE(seat);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @ApiOperation(value = "[수정] Detection", notes = "Detection Server단에서 좌석 정보를 수정한다.")
    @PutMapping("/detection")
    public ResponseEntity<? extends BaseResponseBody> updateDe(
            @ApiParam(value = "좌석 정보", required = true)
            @RequestBody SeatDEUpdateReq seat) {

        seatService.updateDE(seat);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

}
