package com.monopoly.backend.service;

import com.monopoly.backend.domain.entity.User;
import com.monopoly.backend.domain.repository.UserRepository;
import com.monopoly.backend.dto.request.UserSaveReq;
import com.monopoly.backend.dto.response.UserRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     * @param req
     * @return User
     */
    public User save(UserSaveReq req) {
        return userRepository.save(req.toEntity());
    }

    /**
     * 유저 ID로 유저 조회
     * @param id
     * @return UserRes
     */
    public UserRes findById(Long id) {
        // 로그인 로직에 활용하기 위해 예외 던지지 않고 null을 리턴
        User entity = userRepository.findById(id)
                .orElse(null);
//                .orElseThrow(() -> new NoUserIdException("해당 ID를 가진 유저가 없습니다.", ErrorCode.USER_NO_ID));

        if (entity == null) {
            return null;
        }

        return new UserRes(entity);
    }

    /**
     * ID로 엑세스토큰 조회
     * @param id
     * @return
     */
    public String getAccessToken(Long id) {
        return userRepository.getAccessToken(id);
    }

    public Boolean updateAccessToken(User user) {
        if (userRepository.updateAccessToken(user) > 0) {
            return true;
        }

        return false;
    }

}
