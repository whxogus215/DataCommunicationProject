package com.nft.reservation.domain.concert.entity;


import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Concert {

    private Long id;
    private String title;
    private String day;
    private Long runningTime;
    private String place;

    // FK
    private Long rankId;
    private Long hallId;
}
