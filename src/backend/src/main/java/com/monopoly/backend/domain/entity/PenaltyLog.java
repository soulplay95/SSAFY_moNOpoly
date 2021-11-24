package com.monopoly.backend.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 패널티 로그 entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "penalty_log")
public class PenaltyLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "seat_id")
    private Long seatId;

    @Builder
    public PenaltyLog(Long id, Long userId, Long seatId, Long sectionId) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.seatId = seatId;
    }

}
