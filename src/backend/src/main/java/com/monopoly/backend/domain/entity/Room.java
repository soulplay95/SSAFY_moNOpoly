package com.monopoly.backend.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 열람실 entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "library_id")
    private Long libraryId;

    @Column(name = "name")
    private String name;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "seat_counts")
    private Integer seatCounts;

    @Column(name = "section_counts")
    private Integer sectionCounts;

    @Builder
    public Room(Long id, Long libraryId, String name, Integer floor, Integer seatCounts, Integer sectionCounts) {
        this.id = id;
        this.libraryId = libraryId;
        this.name = name;
        this.floor = floor;
        this.seatCounts = seatCounts;
        this.sectionCounts = sectionCounts;
    }

}
