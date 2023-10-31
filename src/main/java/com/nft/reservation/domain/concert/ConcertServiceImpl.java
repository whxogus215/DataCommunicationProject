package com.nft.reservation.domain.concert;

import com.nft.reservation.domain.mapper.ConcertMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final JdbcConcertRepository repository;

    private final ConcertMapper concertMapper;

    @Override
    public ConcertDTO getConcertDetail(Integer concertId) {
        // 컨트롤러에게 전달받은 콘서트 ID로 DAO에 find 메서드 조회
        Optional<Concert> findConcert = repository.findById(concertId);
        // 컨트롤러에서 반환하기 위해 필요한 정보들만 담아서 반환 (DTO 객체)
        if (findConcert.isPresent()) {
            // Optional 객체가 null이 아닐 경우, DTO로 변환해서 반환
            Concert concert = findConcert.get();
            return concertMapper.concertToConcertResponseDTO(concert);
        }
        return null;
    }
}
