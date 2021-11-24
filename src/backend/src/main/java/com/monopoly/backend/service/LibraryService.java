package com.monopoly.backend.service;

import com.monopoly.backend.domain.entity.Library;
import com.monopoly.backend.dto.response.LibraryRes;
import com.monopoly.backend.domain.repository.LibraryRepository;
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
public class LibraryService {

    private final LibraryRepository libraryRepository;

    /**
     * 시도명 리스트를 조회한다.
     * @return List<String>
     */
    public List<String> findSidos() {
        return libraryRepository.findSidos()
                .map(String::new)
                .collect(Collectors.toList());
    }

    /**
     * 시도명으로 구군명 리스트를 조회한다.
     * @param sido
     * @return List<String>
     */
    public List<String> findAllGugunBySido(String sido) {
        return libraryRepository.findAllGugunBySido(sido)
                .map(String::new)
                .collect(Collectors.toList());
    }

    /**
     * 구군명으로 도서관 리스트를 조회한다.
     * @param gugun
     * @return List
     */
    public List<LibraryRes> findAllByGugun(String gugun) {
        return libraryRepository.findAllByGugun(gugun)
                .map(LibraryRes::new)
                .collect(Collectors.toList());
    }

    /**
     * 이름으로 검색
     * @param name
     * @return List
     */
    public LibraryRes getByName(String name) {
        Library entity = libraryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(name + " 이름을 가진 도서관이 없습니다."));

        return new LibraryRes(entity);
    }

    /**
     * ID로 검색
     * @param id
     * @return
     */
    public LibraryRes getById(Long id) {
        Library entity = libraryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " 아이디를 가진 도서관이 없습니다."));

        return new LibraryRes(entity);
    }

}
