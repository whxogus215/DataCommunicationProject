package com.nft.reservation.domain.mint.service;

import com.nft.reservation.domain.mint.repository.H2MintRepository;
import com.nft.reservation.web.mint.dto.MintUrlResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MintServiceImpl implements MintService {
    private final H2MintRepository mintRepository;

    @Override
    public MintUrlResponse getMintImageUrl(Long concertId) {
        Optional<String> mintImageUrl = mintRepository.getMintImageUrl(concertId);

        if (mintImageUrl.isEmpty()) {
            throw new RuntimeException("이미지가 존재하지 않습니다.");
        }
        MintUrlResponse response = new MintUrlResponse();
        response.setImageUrl(mintImageUrl.get());
        response.setConcertId(concertId);

        return response;
    }
}
