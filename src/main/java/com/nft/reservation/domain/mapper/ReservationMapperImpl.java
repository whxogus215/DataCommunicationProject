package com.nft.reservation.domain.mapper;

import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Seat;
import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ConcertDTO concertToConcertResponseDTO(Concert concert) {
        if (concert == null) {
            return null;
        }
        ConcertDTO concertDTO = new ConcertDTO();

        concertDTO.setId(concert.getId());
        concertDTO.setTitle(concert.getTitle());
        concertDTO.setDay(concert.getDay());
        concertDTO.setRunningTime(concert.getRunningTime());


        return concertDTO;
    }

    @Override
    public Concert concertDTOToEntity(ConcertDTO concertDTO) {
        if (concertDTO == null) {
            return null;
        }
        Concert concert = new Concert();

        concert.setTitle(concertDTO.getTitle());
        concert.setDay(concertDTO.getDay());
        concert.setRunningTime(concertDTO.getRunningTime());
        concert.setCastMember(concertDTO.getCastMember());

        return concert;
    }

    @Override
    public SeatDTO seatToSeatResponseDTO(Seat seat) {
        if (seat == null) {
            return null;
        }

        SeatDTO seatDTO = new SeatDTO();

        seatDTO.setRow(seat.getRow());
        seatDTO.setCol(seat.getCol());
        seatDTO.setBooked(seat.isData());

        return seatDTO;
    }

    @Override
    public ConcertHallDTO concertHallToConcertHallDTO(ConcertHall concertHall) {
        if (concertHall == null) {
            return null;
        }

        ConcertHallDTO concertHallDTO = new ConcertHallDTO();

        concertHallDTO.setRowSize(concertHall.getRowSize());
        concertHallDTO.setColumnSize(concertHall.getColumnSize());

        return concertHallDTO;
    }
}
