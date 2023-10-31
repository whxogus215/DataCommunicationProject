package com.nft.reservation.domain.mapper;

import com.nft.reservation.domain.concert.Concert;
import com.nft.reservation.domain.concert.Seat;
import com.nft.reservation.domain.concert.dto.ConcertDTO;
import com.nft.reservation.domain.concert.dto.SeatDTO;

public interface ReservationMapper {
    ConcertDTO concertToConcertResponseDTO(Concert concert);
    SeatDTO seatToSeatResponseDTO(Seat seat);
}
