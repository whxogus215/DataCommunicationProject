package com.nft.reservation.domain.mapper;

import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Seat;
import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;

public interface ReservationMapper {
    ConcertDTO concertToConcertResponseDTO(Concert concert);
    SeatDTO seatToSeatResponseDTO(Seat seat);

    ConcertHallDTO concertHallToConcertHallDTO(ConcertHall concertHall);
}
