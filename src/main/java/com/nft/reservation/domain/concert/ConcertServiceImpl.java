package com.nft.reservation.domain.concert;

import org.springframework.stereotype.Service;

@Service
public class ConcertServiceImpl implements ConcertService {
    @Override
    public void getConcertDetail(Integer concertId) {
        // 컨트롤러에게 전달받은 콘서트 ID로 DAO에 find 메서드 조회
    }
}
