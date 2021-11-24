package com.monopoly.backend.service;

import com.monopoly.backend.api.StringToDateTimeAPI;
import com.monopoly.backend.domain.entity.Image;
import com.monopoly.backend.domain.entity.Locker;
import com.monopoly.backend.dto.request.LockerUpdateReq;
import com.monopoly.backend.dto.response.LockerRes;
import com.monopoly.backend.domain.repository.LockerRepository;
import com.monopoly.backend.dto.request.LockerSaveReq;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class LockerService {

    private final LockerRepository lockerRepository;

    public Locker save(Long libraryId, String title, String content, Long penaltyLogId, String foundDate, MultipartFile file) {
        Locker locker = new Locker();

        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            locker.setContent(content);
            locker.setFoundDate(StringToDateTimeAPI.stringToDateTime(foundDate));
            locker.setLibraryId(libraryId);
            locker.setPenaltyLogId(penaltyLogId);
            locker.setTitle(title);
            locker.setImage(byteObjects);
        } catch (IOException e) {
            log.error("Error occurred", e);

            e.printStackTrace();
        }

        return lockerRepository.save(locker);
    }

    /**
     * ID로 삭제
     * @param id
     */
    public void deleteById(Long id) {
        lockerRepository.deleteById(id);
    }

    /**
     * 도서관 ID로 리스트 조회
     * @param libraryId
     * @return List<LockerRes>
     */
    public List<LockerRes> getListByLibraryId(Long libraryId) {
        return lockerRepository.findAllByLibraryId(libraryId)
                .map(LockerRes::new)
                .collect(Collectors.toList());
    }

    /**
     * 패널티 로그 ID로 리스트 조회
     * @param penaltyLogId
     * @return List<LockerRes>
     */
    public List<LockerRes> getListByPenaltyLogId(Long penaltyLogId) {
        return lockerRepository.findAllByPenaltyLogId(penaltyLogId)
                .map(LockerRes::new)
                .collect(Collectors.toList());
    }

    /**
     * ID로 조회
     * @param id
     * @return LockerRes
     */
    public LockerRes getById(Long id) {
        Locker entity = lockerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 물품보관함이 없습니다."));

        return new LockerRes(entity);
    }

    /**
     * 수정
     * @param req
     */
    public void update(LockerUpdateReq req) {
        lockerRepository.update(req.toEntity());
    }

    /**
     * 만료일로 삭제
     */
    public Long deleteAllByEx() {
        return lockerRepository.deleteAllByEx();
    }

}
