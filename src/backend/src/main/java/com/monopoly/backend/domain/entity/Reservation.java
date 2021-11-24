package com.monopoly.backend.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 예약 entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "max_set_time")
    private Integer maxSetTime;

    @Builder
    public Reservation(Long id, Long userId, Long seatId, LocalDateTime startTime, LocalDateTime endTime, Integer maxSetTime) {
        this.id = id;
        this.userId = userId;
        this.seatId = seatId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxSetTime = maxSetTime;
    }

}
