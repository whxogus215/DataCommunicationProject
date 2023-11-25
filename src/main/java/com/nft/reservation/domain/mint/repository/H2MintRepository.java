package com.nft.reservation.domain.mint.repository;

import java.util.Optional;

public class H2MintRepository implements JdbcMintRepository{
    @Override
    public Long saveMintImageUrl(String url) {
        return null;
    }

    @Override
    public Optional<String> getMintImageUrl(Long concertId) {
        return Optional.empty();
    }
}
