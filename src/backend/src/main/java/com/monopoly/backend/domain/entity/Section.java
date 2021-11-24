package com.monopoly.backend.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 섹션(카메라) entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "section")
public class Section extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "seat_counts")
    private Integer seatCounts;

    @Builder
    public Section(Long id, Long roomId, Integer seatCounts) {
        this.id = id;
        this.roomId = roomId;
        this.seatCounts = seatCounts;
    }

}
