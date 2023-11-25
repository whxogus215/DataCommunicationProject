package com.nft.reservation.domain.mint.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.nft.reservation.domain.concert.repository.H2ConcertRepository;
import com.nft.reservation.domain.concert.repository.JdbcConcertRepository;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class H2MintRepositoryTest {

    private String url = "jdbc:h2:tcp://localhost/~/test";

    private String username = "sa";

    private String password = "";

    JdbcMintRepository jdbcMintRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        jdbcMintRepository = new H2MintRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("민팅 횟수 조회 및 갱신 테스트")
    void mintCountTest() {
        Long findMintCount = jdbcMintRepository.getMintCount();
        jdbcMintRepository.updateMintCount(findMintCount + 1);
        Long updateMintCount = jdbcMintRepository.getMintCount();

        assertThat(updateMintCount).isEqualTo(findMintCount + 1);
    }

    @Test
    @DisplayName("민팅 이미지 url 저장 및 반환 테스트")
    void mintImageUrlTest() {
        String url = "https://metadata-store.klaytnapi.com/c111da93-ef33-87db-0db4-97b3b"
                + "de8a54b/3c46bfa8-1845-52c7-8718-de3181388b28.jfif";
        Long concertId = 1L;

        jdbcMintRepository.saveMintImageUrl(url, concertId);
        Optional<String> mintImageUrl = jdbcMintRepository.getMintImageUrl(concertId);

        assertThat(mintImageUrl).isEqualTo(Optional.of(url));
    }
}
