package com.nft.reservation.web.concert.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ConcertForm {
    private ConcertDTO concertDTO;

    private Long hallCapacity;
    private Long hallRow;
    private Long hallColumn;

    private MultipartFile thumbnailImage;
    private MultipartFile carouselImage;
    private List<MultipartFile> contentImages;

    private String mintImageUrl;
}
