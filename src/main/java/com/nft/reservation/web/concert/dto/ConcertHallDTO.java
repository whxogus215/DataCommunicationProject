package com.nft.reservation.web.concert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConcertHallDTO {
    private String name;
    private Long capacity;
    private Long rowSize;
    private Long columnSize;
    private String address;
}
