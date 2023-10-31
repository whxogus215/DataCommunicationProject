package com.nft.reservation.domain.mapper;

import com.nft.reservation.domain.concert.Concert;
import com.nft.reservation.domain.concert.Seat;
import com.nft.reservation.domain.concert.dto.ConcertDTO;
import com.nft.reservation.domain.concert.dto.SeatDTO;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ConcertDTO concertToConcertResponseDTO(Concert concert) {
        if ( concert == null ) {
            return null;
        }

        ConcertDTO concertDTO = new ConcertDTO();

        concertDTO.setId( concert.getId() );
        concertDTO.setTitle( concert.getTitle() );
        concertDTO.setDate( concert.getDate() );
        concertDTO.setRunningTime( concert.getRunningTime() );
        concertDTO.setPlace( concert.getPlace() );

        return concertDTO;
    }

    @Override
    public SeatDTO seatToSeatResponseDTO(Seat seat) {
        if ( seat == null ) {
            return null;
        }

        SeatDTO seatDTO = new SeatDTO();

        seatDTO.setRow( seat.getRow() );
        seatDTO.setCol( seat.getCol() );
        seatDTO.setData( seat.isData() );

        return seatDTO;
    }
}
