package com.nft.reservation.domain.concert;

import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;
import com.nft.reservation.domain.mapper.ReservationMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final JdbcConcertRepository repository;

    private final ReservationMapper mapper;

    @Override
    public ConcertDTO getConcertDetail(Integer concertId) {
        // 컨트롤러에게 전달받은 콘서트 ID로 DAO에 find 메서드 조회
        Optional<Concert> findConcert = repository.findById(concertId);
        // 컨트롤러에서 반환하기 위해 필요한 정보들만 담아서 반환 (DTO 객체)
        if (findConcert.isPresent()) {
            // Optional 객체가 null이 아닐 경우, DTO로 변환해서 반환
            Concert concert = findConcert.get();
            return mapper.concertToConcertResponseDTO(concert);
        }
        return null;
    }

    @Override
    public List<SeatDTO> getConcertSeat(Integer concertId) {
        List<Seat> bookedSeats = repository.findBookedSeatById(concertId);

        List<SeatDTO> dtoBookedSeats = new ArrayList<>();
        for (Seat bookedSeat : bookedSeats) {

            SeatDTO seatDTO = mapper.seatToSeatResponseDTO(bookedSeat);
            dtoBookedSeats.add(seatDTO);
        }


        return dtoBookedSeats;
    }
}
