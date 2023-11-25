package com.nft.reservation.domain.mint.repository;

import java.util.Optional;

public interface JdbcMintRepository {
    Long saveMintImageUrl(String url);
    Optional<String> getMintImageUrl(Long concertId);
}
