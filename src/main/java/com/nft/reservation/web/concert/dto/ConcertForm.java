package com.nft.reservation.web.concert.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ConcertForm {
    private ConcertDTO concertDTO;

    private String hallCapacity;
    private String hallRow;
    private String hallColumn;

    private MultipartFile thumbnailImage;
    private MultipartFile carouselImage;
    private List<MultipartFile> contentImages;
}
