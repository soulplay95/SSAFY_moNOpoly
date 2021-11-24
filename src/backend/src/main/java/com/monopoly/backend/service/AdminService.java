package com.monopoly.backend.service;

import com.monopoly.backend.domain.entity.Admin;
import com.monopoly.backend.domain.repository.UserRepository;
import com.monopoly.backend.domain.repository.AdminRepository;
import com.monopoly.backend.dto.request.AdminSaveReq;
import com.monopoly.backend.dto.response.AdminRes;
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
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    /**
     * 등록
     * @param req
     * @return entity
     */
    public Admin save(AdminSaveReq req) {
        userRepository.update(userRepository.getById(req.getId())); // 타입 변경

        return adminRepository.save(req.toEntity());
    }

    /**
     * 도서관 ID로 리스트 조회
     * @param libraryId
     * @return List
     */
    public List<AdminRes> getListByLibraryId(Long libraryId) {
        return adminRepository.findAllByLibraryId(libraryId)
                .map(AdminRes::new)
                .collect(Collectors.toList());
    }

    /**
     * ID로 삭제
     * @param id
     */
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }

}
