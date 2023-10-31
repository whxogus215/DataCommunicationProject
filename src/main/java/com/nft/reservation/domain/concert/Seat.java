package com.nft.reservation.domain.concert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat {
    // PK
    private Integer id;

    private Integer row;
    private Integer column;
    private boolean isBook;

    // FK
    private Integer concert_id;
    private Integer member_id;
}
