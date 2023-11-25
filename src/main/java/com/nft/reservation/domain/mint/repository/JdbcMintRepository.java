package com.nft.reservation.domain.mint.repository;

import java.util.Optional;

public interface JdbcMintRepository {
    void saveMintImageUrl(String url, Long concertId);
    Optional<String> getMintImageUrl(Long concertId);
}
