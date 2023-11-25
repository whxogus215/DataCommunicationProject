package com.nft.reservation.domain.mint.repository;

import java.util.Optional;

public interface JdbcMintRepository {
    Long updateMintCount(Long count);
    Long getMintCount();

    void saveMintImageUrl(String url, Long concertId);
    Optional<String> getMintImageUrl(Long concertId);
}
