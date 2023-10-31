package com.nft.reservation.domain.concert;

import com.nft.reservation.domain.concert.dto.ConcertDTO;

public interface ConcertService {
    ConcertDTO getConcertDetail(Integer concertId);
}
