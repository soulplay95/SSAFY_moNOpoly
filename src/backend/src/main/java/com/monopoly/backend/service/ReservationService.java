package com.monopoly.backend.service;

import com.monopoly.backend.domain.entity.Reservation;
import com.monopoly.backend.domain.repository.ReservationRepository;
import com.monopoly.backend.dto.request.ReservationSaveReq;
import com.monopoly.backend.dto.response.ReservationRes;
import com.monopoly.backend.exception.ErrorCode;
import com.monopoly.backend.exception.NoSeatIdException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    /**
     * 등록
     * @param req
     * @return res
     */
    public ReservationRes save(ReservationSaveReq req) {
        Reservation entity = reservationRepository.save(req.toEntity());

        return new ReservationRes(entity);
    }

    /**
     * ID로 찾기
     * @param seatId
     * @return
     */
    public ReservationRes getBySeatId(Long seatId) {
        Reservation entity = reservationRepository.findBySeatId(seatId)
                .orElseThrow(() -> new NoSeatIdException(ErrorCode.SEAT_NO_ID));

        return new ReservationRes(entity);
    }

    /**
     * ID로 삭제
     * @param id
     */
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

}
