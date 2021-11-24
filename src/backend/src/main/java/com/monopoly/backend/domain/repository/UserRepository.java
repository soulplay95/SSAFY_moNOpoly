package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    // [등록]
    User save(User user);

    // [조회] By 유저 ID
    Optional<User> findById(Long id);

    // 엑세스토큰 조회 By ID
    @Query("SELECT u.accessToken FROM User u WHERE u.id=:id")
    String getAccessToken(@Param("id") Long id);

}
