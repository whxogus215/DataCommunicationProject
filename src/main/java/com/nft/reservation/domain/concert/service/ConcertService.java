package com.nft.reservation.domain.concert.service;

import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertForm;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;
import com.nft.reservation.web.concert.dto.SeatResponse;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public interface ConcertService {
    ConcertDTO createConcert(ConcertForm concertForm);
    ConcertDTO getConcertDetail(Long concertId);

    List<SeatDTO> getConcertSeat(Long concertId);
    SeatResponse reserveSeats(Long concertId, List<SeatDTO> seatDTOs);

    ConcertHallDTO getConcertHallSize(Long concertId);

    Resource getDownloadImage(String fileName) throws MalformedURLException;
}
