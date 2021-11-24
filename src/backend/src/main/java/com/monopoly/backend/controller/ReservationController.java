package com.monopoly.backend.controller;

import com.monopoly.backend.dto.request.ReservationSaveReq;
import com.monopoly.backend.dto.response.BaseResponseBody;
import com.monopoly.backend.dto.response.ReservationRes;
import com.monopoly.backend.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "예약 API", tags = {"Reservation"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @ApiOperation(value = "[등록]", notes = "예약 정보를 입력받아 예약을 등록한다.")
    @PostMapping
    private ResponseEntity<ReservationRes> addReservation(
            @ApiParam(value = "예약 정보", required = true)
            @RequestBody ReservationSaveReq reservation) {

        return ResponseEntity.status(200).body(reservationService.save(reservation));
    }

    @ApiOperation(value = "[조회] By 좌석 ID", notes = "좌석 번호를 입력받아 예약 상세 조회")
    @GetMapping("/{seatId}")
    private ResponseEntity<ReservationRes> getReservation(
            @ApiParam(value = "좌석 ID", required = true)
            @PathVariable Long seatId) {

        return ResponseEntity.status(200).body(reservationService.getBySeatId(seatId));
    }

    @ApiOperation(value = "[삭제] By ID", notes = "예약 ID에 해당하는 예약 정보를 삭제한다.")
    @DeleteMapping("/{id}")
    private ResponseEntity<? extends BaseResponseBody> deleteReservation(
            @ApiParam(value = "예약 ID", required = true)
            @PathVariable Long id) {

        reservationService.deleteById(id);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "OK"));
    }

}
