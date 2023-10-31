package com.nft.reservation.domain.concert.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConcertDTO {
    private Integer id;
    private String title;
    private Date date;
    private Integer runningTime;
    private String place;
}
