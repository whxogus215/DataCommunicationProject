package com.nft.reservation.domain.concert.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {

    private String uploadName; // 업로드 이름
    private String storeName; // UUID 이름
    private Long concertId; // 콘서트 ID (FK)

    public Image(String uploadName, String storeName) {
        this.uploadName = uploadName;
        this.storeName = storeName;
    }

}
