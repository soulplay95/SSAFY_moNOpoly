package com.monopoly.backend.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 도서관 entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sido")
    private String sido;

    @Column(name = "gugun")
    private String gugun;

    @Column(name = "seat_counts")
    private String seatCounts;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "url")
    private String url;

    @Builder
    public Library(Long id, String name, String sido, String gugun, String seatCounts, String address, String phone, String url) {
        this.id = id;
        this.name = name;
        this.sido = sido;
        this.gugun = gugun;
        this.seatCounts = seatCounts;
        this.address = address;
        this.phone = phone;
        this.url = url;
    }

}
