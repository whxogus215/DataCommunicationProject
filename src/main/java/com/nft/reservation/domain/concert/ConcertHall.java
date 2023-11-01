package com.nft.reservation.domain.concert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConcertHall {
    private Integer id;
    private String name;
    private Integer capacity;
    private Integer rowSize;
    private Integer columnSize;
}
