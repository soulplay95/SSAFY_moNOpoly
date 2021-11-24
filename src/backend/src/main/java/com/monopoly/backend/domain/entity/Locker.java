package com.monopoly.backend.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 물품보관함 entity
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "locker")
public class Locker extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "penalty_log_id")
    private Long penaltyLogId;

    @Column(name = "library_id")
    private Long libraryId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Column(name = "found_date")
    private LocalDateTime foundDate;

    @Lob
    @Column(name = "image")
    private Byte[] image;

    @Builder
    public Locker(Long id, Long penaltyLogId, Long libraryId, String title, String content, LocalDateTime expireDate, LocalDateTime foundDate, Byte[] image) {
        this.id = id;
        this.penaltyLogId = penaltyLogId;
        this.libraryId = libraryId;
        this.title = title;
        this.content = content;
        this.expireDate = expireDate;
        this.foundDate = foundDate;
        this.image = image;
    }

}
