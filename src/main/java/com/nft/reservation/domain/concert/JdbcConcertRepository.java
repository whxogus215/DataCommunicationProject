package com.nft.reservation.domain.concert;

import java.util.List;
import java.util.Optional;

public interface JdbcConcertRepository {
    Optional<Concert> findById(Integer id);
    List<Seat> findBookedSeatById(Integer id);

    Optional<ConcertHall> findConcertHallById(Integer id);
}
