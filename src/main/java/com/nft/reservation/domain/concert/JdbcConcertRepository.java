package com.nft.reservation.domain.concert;

import java.util.Optional;

public interface JdbcConcertRepository {
    Optional<Concert> findById(Integer id);
}
