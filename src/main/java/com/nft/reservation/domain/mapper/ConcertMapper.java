package com.nft.reservation.domain.mapper;

import com.nft.reservation.domain.concert.Concert;
import com.nft.reservation.domain.concert.dto.ConcertDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConcertMapper {

    ConcertDTO concertToConcertResponseDTO(Concert concert);
}
