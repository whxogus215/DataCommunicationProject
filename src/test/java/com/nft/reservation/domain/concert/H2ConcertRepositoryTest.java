package com.nft.reservation.domain.concert;

import static org.assertj.core.api.Assertions.*;

import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Seat;
import com.zaxxer.hikari.HikariDataSource;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootTest
class H2ConcertRepositoryTest {

    private String url = "jdbc:h2:tcp://localhost/~/test";

    private String username = "sa";

    private String password = "";

    JdbcConcertRepository jdbcConcertRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        jdbcConcertRepository = new H2ConcertRepository(jdbcTemplate);

//        jdbcConcertRepository.deleteAll();
//        jdbcConcertRepository.resetAIColumn();
    }

    @Test
    @DisplayName("특정 공연 조회 메서드 테스트")
    void findByIdTest() {
        int testId = 1;

        Optional<Concert> findConcert = jdbcConcertRepository.findById(testId);

        assertThat(findConcert).isNotNull();

        log.info("조회한 Concert 출력 : {}", findConcert.get());
    }

    @Test
    @DisplayName("특정 공연의 좌석 조회 메서드 테스트")
    void findBookedSeatByIdTest() {
        int testId = 1;

        List<Seat> bookedSeatById = jdbcConcertRepository.findBookedSeatById(testId);

        bookedSeatById.stream()
                .forEach((seat) -> log.info("조회한 Seat 출력 : {}", seat.toString()));
    }

    @Test
    @DisplayName("특정 공연의 공연장 조회 메서드 테스트")
    void findConcertHallByIdTest() {
        int testId = 1;

        Optional<ConcertHall> concertHallById = jdbcConcertRepository.findConcertHallById(testId);

        concertHallById
                .ifPresent((concertHall) -> log.info("조회한 ConcertHall 출력 : {}", concertHall.toString()));
    }
}
