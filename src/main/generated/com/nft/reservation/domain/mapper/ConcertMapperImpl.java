package com.nft.reservation.domain.mapper;

import com.nft.reservation.domain.concert.Concert;
import com.nft.reservation.domain.concert.ConcertDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-31T15:28:27+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ConcertMapperImpl implements ConcertMapper {

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
}
