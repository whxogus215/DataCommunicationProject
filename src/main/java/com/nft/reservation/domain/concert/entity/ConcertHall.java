package com.nft.reservation.domain.concert.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConcertHall {
    private Integer id;
    private String name;
    private Integer capacity;
    private Integer rowSize;
    private Integer columnSize;
    private String address;
}
