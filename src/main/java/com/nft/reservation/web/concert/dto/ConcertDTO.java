package com.nft.reservation.web.concert.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConcertDTO {
    @Schema(description = "공연 ID")
    private Long id;

    @Schema(description = "공연 제목")
    private String title;

    @Schema(description = "공연 날짜")
    private String day;

    @Schema(description = "관람 등급")
    private String rate;

    @Schema(description = "공연 시간")
    private Long runningTime;

    @Schema(description = "출연진")
    private String castMember;

    @Schema(description = "공연장 이름")
    private String hallName;

    @Schema(description = "공연장 주소")
    private String hallAddress;

    @Schema(description = "썸네일 사진 URL")
    private String thumbnailUrl;

    @Schema(description = "캐러셀 사진 URL")
    private String carouselUrl;

    @Schema(description = "상세 설명에 들어가는 사진 URL 리스트")
    private List<String> images;
}
