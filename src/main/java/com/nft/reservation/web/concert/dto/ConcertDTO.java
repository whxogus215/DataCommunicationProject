package com.nft.reservation.web.concert.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConcertDTO {
    private Integer id;
    private String title;
    private String day;
    private Integer runningTime;
    private String place;
}
