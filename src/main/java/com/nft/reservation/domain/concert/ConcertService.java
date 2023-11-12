package com.nft.reservation.domain.concert;

import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertForm;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;
import java.util.List;

public interface ConcertService {
    ConcertDTO getConcertDetail(Integer concertId);
    List<SeatDTO> getConcertSeat(Integer concertId);
    ConcertHallDTO getConcertHallSize(Integer concertId);
    void createConcert(ConcertForm concertForm);
}
