package com.nft.reservation.web.concert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConcertHallDTO {
    private String name;
    private Integer capacity;
    private Integer rowSize;
    private Integer columnSize;
}
