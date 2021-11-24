package com.monopoly.backend.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 도서관별 관리자 entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "admin")
public class Admin extends BaseTimeEntity {

    @Id
    private Long id;

    @Column(name = "library_id")
    private Long libraryId;

    @Builder
    public Admin(Long id, Long libraryId) {
        this.id = id;
        this.libraryId = libraryId;
    }

}
