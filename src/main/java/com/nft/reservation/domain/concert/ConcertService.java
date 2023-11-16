package com.nft.reservation.domain.concert;

import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertForm;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public interface ConcertService {
    ConcertDTO createConcert(ConcertForm concertForm);
    ConcertDTO getConcertDetail(Long concertId);
    List<SeatDTO> getConcertSeat(Long concertId);
    ConcertHallDTO getConcertHallSize(Long concertId);

    Resource getDownloadImage(String fileName) throws MalformedURLException;
}
