package com.nft.reservation.domain.concert;


import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Concert {
    private Integer id;
    private String title;
    private Date date;
    private Integer runningTime;
    private String place;

    // FK
    private Integer rankId;
    private Integer hallId;
}
