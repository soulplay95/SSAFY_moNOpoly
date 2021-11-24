package com.monopoly.backend.domain.repository;

import com.monopoly.backend.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    // [등록]
    Admin save(Admin admin);

    // [삭제] By ID
    void deleteById(Long id);

    // [조회] 리스트 By 도서관 ID
    Stream<Admin> findAllByLibraryId(Long libraryId);
    
}
