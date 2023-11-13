package com.nft.reservation.domain.concert;

import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Seat;
import java.util.List;
import java.util.Optional;

public interface JdbcConcertRepository {
    Optional<Concert> findById(Integer id);
    List<Seat> findBookedSeatById(Integer id);
    Optional<ConcertHall> findConcertHallById(Integer id);

    Integer findConcertHallIdByName(String name);

    Integer findRankIdByName(String name);
}
