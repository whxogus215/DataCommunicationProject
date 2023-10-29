package com.nft.reservation.domain.concert;

import static org.assertj.core.api.Assertions.*;

import com.nft.reservation.domain.board.H2BoardRepository;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class H2ConcertRepositoryTest {

    private String url = "jdbc:h2:tcp://localhost/~/test";

    private String username = "sa";

    private String password = "";

    JdbcConcertRepository jdbcConcertRepository;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        jdbcConcertRepository = new H2ConcertRepository(dataSource);

//        jdbcConcertRepository.deleteAll();
//        jdbcConcertRepository.resetAIColumn();
    }

    @Test
    @DisplayName("특정 공연 조회 메서드 테스트")
    void findByIdTest() {
        int testId = 1;

        Optional<Concert> findConcert = jdbcConcertRepository.findById(testId);

        assertThat(findConcert).isNotNull();
    }
}