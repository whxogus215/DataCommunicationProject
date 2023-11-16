package com.nft.reservation.domain.concert;

import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Image;
import com.nft.reservation.domain.concert.entity.Seat;
import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import java.util.List;
import java.util.Optional;

public interface JdbcConcertRepository {
    Long saveConcert(Concert concert);
    Optional<ConcertDTO> findConcertById(Long id);

    List<Seat> findBookedSeatById(Long id);

    Long saveHall(ConcertHallDTO concertHallDTO);

    Long findConcertHallIdByName(String name);

    Optional<ConcertHall> findConcertHallById(Long id);


    Long saveRank(String detail);
    Long findRankIdByDetail(String detail);

    Long saveImage(Image image);

    List<Image> findImageByConcertId(Long id);
}
