package com.nft.reservation.web.concert.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConcertDTO {
    private Long id;
    private String title;
    private String day;
    private String rate; // 관람 등급
    private Long runningTime;
    private String castMember;

    private String hallName; // 공연장 이름
    private String hallAddress; // 공연장 주소

    private String thumbnailUrl; // 썸네일 사진
    private String carouselUrl; // 캐러셀 사진
    private List<String> images; // 본문에 들어가는 소개 사진
}
