package com.nft.reservation.domain.concert;

import static org.assertj.core.api.Assertions.*;

import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Image;
import com.nft.reservation.domain.concert.entity.Seat;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.zaxxer.hikari.HikariDataSource;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
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
        long testId = 1;

        Optional<ConcertHall> concertHallById = jdbcConcertRepository.findConcertHallById(testId);

        concertHallById
                .ifPresent((concertHall) -> log.info("조회한 ConcertHall 출력 : {}",
                        concertHall.toString()));
    }

    @Test
    @DisplayName("공연장 이름으로 공연 ID 값 조회 메서드 테스트")
    void findConcertHallIdByNameTest() {
        String name = "KSPO DOME(올림픽체조경기장)";
        Long findId = jdbcConcertRepository.findConcertHallIdByName(name);

        assertThat(findId).isEqualTo(1);
    }

    @Test
    @DisplayName("공연장 이름으로 공연 ID 값 조회 메서드 예외 테스트")
    void findConcertHallIdByNameNullTest() {
        String name = "KSPO";
        Long findId = jdbcConcertRepository.findConcertHallIdByName(name);
        assertThat(findId).isNull();
    }

    @Test
    @DisplayName("이미지 저장 테스트")
    void saveImageTest() {
        Image image = new Image("테스트1", "테스트1 업로드", 4L);

        jdbcConcertRepository.saveImage(image);
        Image findImage = jdbcConcertRepository.findImageByConcertId(4L);

        assertThat(image.getUploadName()).isEqualTo(findImage.getUploadName());
    }
}
