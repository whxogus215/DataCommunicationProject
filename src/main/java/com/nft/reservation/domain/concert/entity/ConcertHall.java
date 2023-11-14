package com.nft.reservation.domain.concert.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConcertHall {
    private Long id;
    private String name;
    private Long capacity;
    private Long rowSize;
    private Long columnSize;
    private String address;
}
