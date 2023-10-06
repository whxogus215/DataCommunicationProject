package com.nft.board.domain.board;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class H2BoardRepositoryTest {

    private String url = "jdbc:h2:tcp://localhost/~/test";

    private String username = "sa";

    private String password = "";

    JdbcBoardRepository jdbcBoardRepository;


    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        jdbcBoardRepository = new H2BoardRepository(dataSource);
    }



    @Test
    @DisplayName("게시판 POST 테스트")
    void saveBoard() {
        Board board = new Board();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");
        board.setPrice(1000);

        Board result = jdbcBoardRepository.saveBoard(board);

        log.info("제목={}, 내용={}, 가격={}, 생성 날짜={}",
                result.getContent(), result.getContent(), result.getPrice(), result.getCreatedDate());


    }
}