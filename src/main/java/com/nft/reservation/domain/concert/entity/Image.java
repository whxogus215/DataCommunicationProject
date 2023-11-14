package com.nft.reservation.domain.concert.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private String uploadName; // 업로드 이름
    private String storeName; // UUID 이름
    private Long concertId; // 콘서트 ID (FK)

}
