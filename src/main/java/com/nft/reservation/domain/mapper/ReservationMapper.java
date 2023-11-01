package com.nft.reservation.domain.mapper;

import com.nft.reservation.domain.concert.Concert;
import com.nft.reservation.domain.concert.ConcertHall;
import com.nft.reservation.domain.concert.Seat;
import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;

public interface ReservationMapper {
    ConcertDTO concertToConcertResponseDTO(Concert concert);
    SeatDTO seatToSeatResponseDTO(Seat seat);

    ConcertHallDTO concertHallToConcertHallDTO(ConcertHall concertHall);
}
