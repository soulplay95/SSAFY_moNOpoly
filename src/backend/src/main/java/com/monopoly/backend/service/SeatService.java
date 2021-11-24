package com.monopoly.backend.service;

import com.monopoly.backend.api.KakaoMessageAPI;
import com.monopoly.backend.domain.entity.Seat;
import com.monopoly.backend.domain.repository.PenaltyLogRepository;
import com.monopoly.backend.domain.repository.SeatRepository;
import com.monopoly.backend.domain.repository.UserRepository;
import com.monopoly.backend.dto.request.PenaltyLogSaveReq;
import com.monopoly.backend.dto.request.SeatDEUpdateReq;
import com.monopoly.backend.dto.request.SeatSaveReq;
import com.monopoly.backend.dto.request.SeatFEUpdateReq;
import com.monopoly.backend.dto.response.SeatRes;
import com.monopoly.backend.exception.*;
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
public class SeatService {

    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final PenaltyLogRepository penaltyLogRepository;

    /**
     * 등록
     * @param req
     * @return Seat
     */
    public Seat save(SeatSaveReq req) {
        // id 중복 체크
        if (findById(req.getId(), req.getSectionId()) == null) {
            return seatRepository.save(req.toEntity());
        } else {
            throw new SeatIdDuplicatedException(ErrorCode.SEAT_ID_DUPLICATED);
        }
    }

    /**
     * ID로 조회
     * @param id
     * @return response
     */
    public SeatRes findById(Long id, Long sectionId) {
        Seat entity = seatRepository.findByIdAndSectionId(id, sectionId)
                .orElse(null);
//                .orElseThrow(() -> new NoSeatIdException(ErrorCode.SEAT_NO_ID));

        if (entity == null) {
            return null;
        }

        return new SeatRes(entity);
    }

    /**
     * 유저 ID로 조회
     * @param userId
     * @return res
     */
    public SeatRes findByUserId(Long userId) {
        Seat entity = seatRepository.findByUserId(userId)
                .orElse(null);

        if (entity == null) {
            return null;
        }

        return new SeatRes(entity);
    }

    /**
     * Section ID로 리스트 조회
     * @param sectionId
     * @return List<Seat>
     */
    public List<SeatRes> findAllBySectionId(Long sectionId) {
        List<SeatRes> list = seatRepository.findAllBySectionId(sectionId)
                .map(SeatRes::new)
                .collect(Collectors.toList());

        if (list.size() == 0) {
            throw new NoSectionIdException(ErrorCode.SEAT_NO_SECTION_ID);
        }

        return list;
    }

    /**
     * 업데이트 - FE
     * @param req
     * @return boolean
     */
    public Boolean updateFE(SeatFEUpdateReq req) {
        if (seatRepository.updateFE(req.toEntity()) == 0) {
            throw new FailSeatUpdateException(ErrorCode.SEAT_FAIL_UPDATE);
        }

        return true;
    }

    /**
     * 업데이트 - Detection
     * @param req
     * @return boolean
     */
    public Boolean updateDE(SeatDEUpdateReq req) {
        if (req.getCamState() == 2) {
            // 사석화 30분 경과
            // 업데이트
            if (seatRepository.update30(req.toEntity()) == 0) {
                throw new FailSeatUpdateException(ErrorCode.SEAT_FAIL_UPDATE);
            }
            // 카톡 메시지 발송
            // 엑세스 토큰 가져오기
            Long userId = seatRepository.findByIdAndSectionId(req.getId(), req.getSectionId()).get().getUserId();
            String accessToken = userRepository.getAccessToken(userId);
            KakaoMessageAPI api = new KakaoMessageAPI();
            api.sendMessage(accessToken, 30, req.getDetectionTime());
        } else if (req.getCamState() == 3) {
            // 사석화 1시간 경과
            // 카톡 메시지 발송
            Long userId = seatRepository.findByIdAndSectionId(req.getId(), req.getSectionId()).get().getUserId();
            System.out.println(userId);
            String accessToken = userRepository.getAccessToken(userId);
            KakaoMessageAPI api = new KakaoMessageAPI();
            api.sendMessage(accessToken, 60, req.getDetectionTime());
            // 퇴실 조치(DB 초기화) + 패널티 로그 생성
            PenaltyLogSaveReq penaltyLogSaveReq = PenaltyLogSaveReq.builder()
                    .userId(userId)
                    .seatId(req.toEntity().getId())
                    .sectionId(req.toEntity().getSectionId())
                    .build();

            penaltyLogRepository.save(penaltyLogSaveReq.toEntity());
            // 업데이트
            if (seatRepository.update60(req.toEntity()) == 0) {
                throw new FailSeatUpdateException(ErrorCode.SEAT_FAIL_UPDATE);
            }
        } else {
            if (seatRepository.updateDE(req.toEntity()) == 0) {
                throw new FailSeatUpdateException(ErrorCode.SEAT_FAIL_UPDATE);
            }
        }

        return true;
    }

}
