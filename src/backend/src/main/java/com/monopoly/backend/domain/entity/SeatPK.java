package com.monopoly.backend.domain.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 좌석 entity 복합키
 */

@Data
public class SeatPK implements Serializable {

    private Long id;

    private Long sectionId;

}
