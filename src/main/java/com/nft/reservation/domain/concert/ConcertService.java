package com.nft.reservation.domain.concert;

import com.nft.reservation.domain.concert.dto.ConcertDTO;
import com.nft.reservation.domain.concert.dto.SeatDTO;
import java.util.List;

public interface ConcertService {
    ConcertDTO getConcertDetail(Integer concertId);
    List<SeatDTO> getConcertSeat(Integer concertId);
}
