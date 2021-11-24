package com.monopoly.backend.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 즐겨찾기 entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "bookmark")
public class Bookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "library_id")
    private Long libraryId;

    @Builder
    public Bookmark(Long id, Long userId, Long libraryId) {
        this.id = id;
        this.userId = userId;
        this.libraryId = libraryId;
    }

}
