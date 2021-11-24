package com.monopoly.backend.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 공지사항 entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "notice")
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "library_id")
    private Long libraryId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "is_head")
    private Boolean isHead;

    @Column(name = "hit_cnt")
    private Long hitCnt;

    @Builder
    public Notice(Long id, Long userId, Long libraryId, String title, String content, Boolean isHead, Long hitCnt) {
        this.id = id;
        this.userId = userId;
        this.libraryId = libraryId;
        this.title = title;
        this.content = content;
        this.isHead = isHead;
        this.hitCnt = hitCnt;
    }

}
