package com.monopoly.backend.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 좌석 entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "seat")
@IdClass(SeatPK.class)
public class Seat implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    @Column(name = "front_state")
    private Integer frontState;

    @Column(name = "cam_state")
    private Integer camState;

    @Column(name = "coordinate_x")
    private Integer coordinateX;

    @Column(name = "coordinate_y")
    private Integer coordinateY;

    @Column(name = "fe_coordinate_x")
    private Integer feCoordinateX;

    @Column(name = "fe_coordinate_y")
    private Integer feCoordinateY;

    @Column(name = "detection_time")
    private LocalDateTime detectionTime;

    @Builder
    public Seat(Long id, Long userId, Long sectionId, Integer frontState, Integer camState, Integer coordinateX, Integer coordinateY, Integer feCoordinateX, Integer feCoordinateY, LocalDateTime detectionTime) {
        this.id = id;
        this.userId = userId;
        this.sectionId = sectionId;
        this.frontState = frontState;
        this.camState = camState;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.feCoordinateX = feCoordinateX;
        this.feCoordinateY = feCoordinateY;
        this.detectionTime = detectionTime;
    }

}
