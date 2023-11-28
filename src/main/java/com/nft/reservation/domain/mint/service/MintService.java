package com.nft.reservation.domain.mint.service;

import com.nft.reservation.web.mint.dto.MintUrlResponse;

public interface MintService {
    MintUrlResponse getMintImageUrl(Long concertId);
}
