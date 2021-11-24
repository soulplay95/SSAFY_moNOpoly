package com.monopoly.backend.domain.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 이미지 entity
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image")
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "board_type")
    private String boardType;

    @Column(name = "board_id")
    private Long boardId;

    @Lob
    @Column(name = "img")
    private Byte[] img;

    @Builder
    public Image(Long id, String boardType, Long boardId, Byte[] img) {
        this.id = id;
        this.boardType = boardType;
        this.boardId = boardId;
        this.img = img;
    }
    
}
