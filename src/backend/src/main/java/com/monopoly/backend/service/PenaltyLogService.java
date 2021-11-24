package com.monopoly.backend.service;

import com.monopoly.backend.domain.entity.PenaltyLog;
import com.monopoly.backend.dto.request.PenaltyLogSaveReq;
import com.monopoly.backend.dto.response.PenaltyLogRes;
import com.monopoly.backend.domain.repository.PenaltyLogRepository;
import com.monopoly.backend.exception.ErrorCode;
import com.monopoly.backend.exception.NoPenaltyLogBySeatIdException;
import com.monopoly.backend.exception.NoPenaltyLogByUserIdException;
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
public class PenaltyLogService {

    private final PenaltyLogRepository penaltyLogRepository;

    /**
     * 등록
     * @param req
     * @return entity
     */
    public PenaltyLog save(PenaltyLogSaveReq req) {
        return penaltyLogRepository.save(req.toEntity());
    }

    /**
     * 유저 ID로 패널티 로그 목록을 조회한다.
     * @param userId
     * @return List<response>
     */
    public List<PenaltyLogRes> findAllByUserId(Long userId) {
        List<PenaltyLogRes> list = penaltyLogRepository.findAllByUserId(userId)
                .map(PenaltyLogRes::new)
                .collect(Collectors.toList());

        if (list.size() == 0) {
            throw new NoPenaltyLogByUserIdException(ErrorCode.PENALTY_LOG_NO_USER_ID);
        }

        return list;
    }

    /**
     * 좌석 ID로 패널티 로그 목록을 조회한다.
     * @param seatId
     * @return List<response>
     */
    public List<PenaltyLogRes> findAllBySeatId(Long seatId) {
        List<PenaltyLogRes> list = penaltyLogRepository.findAllBySeatId(seatId)
                .map(PenaltyLogRes::new)
                .collect(Collectors.toList());

        if (list.size() == 0) {
            throw new NoPenaltyLogBySeatIdException(ErrorCode.PENALTY_LOG_NO_SEAT_ID);
        }

        return list;
    }

}
