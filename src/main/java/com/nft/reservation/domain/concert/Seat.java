package com.nft.reservation.domain.concert;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Seat {
    // PK
    private Integer id;

    private Integer row;
    private String column;
    private boolean isBook;

    // FK
    private Integer concert_id;
    private Integer member_id;
}
