package com.nft.board.domain.board;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class H2BoardRepositoryTest {

    private String url = "jdbc:h2:tcp://localhost/~/test";

    private String username = "sa";

    private String password = "";

    private int testId = 1;

    JdbcBoardRepository jdbcBoardRepository;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        jdbcBoardRepository = new H2BoardRepository(dataSource);

        jdbcBoardRepository.deleteAll();
        jdbcBoardRepository.resetAIColumn();
    }

    @Test
    @DisplayName("게시판 POST 테스트")
    void saveBoard() {
        //given
        Board board = new Board();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");
        board.setPrice(1000);

        //when
        jdbcBoardRepository.saveBoard(board);
    }

    @Test
    @DisplayName("게시판 GET 테스트")
    void getBoard() {
        //given
        Board board = new Board();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");
        board.setPrice(1000);

        //when
        jdbcBoardRepository.saveBoard(board);
        Board findBoard = jdbcBoardRepository.findBoardById(testId);

        //then
        log.info("게시판 조회 결과 = {}",findBoard);
    }

    @Test
    @DisplayName("게시판 UPDATE 테스트")
    void updateBoard() {
        //given
        Board board = new Board();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");
        board.setPrice(1000);

        jdbcBoardRepository.saveBoard(board);

        //when
        Board updateBoard = new Board();

        updateBoard.setBoardId(testId);

        updateBoard.setTitle("테스트 제목 업데이트");
        updateBoard.setContent("테스트 내용 업데이트");
        updateBoard.setPrice(8000);

        jdbcBoardRepository.updateBoard(updateBoard);

        //then
        Board findBoard = jdbcBoardRepository.findBoardById(testId);
        assertThat(findBoard.getContent()).isEqualTo(updateBoard.getContent());
    }


    @Test
    @DisplayName("게시판 DELETE 테스트")
    void deleteBoard() {
        //given
        Board board = new Board();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");
        board.setPrice(1000);

        //when
        jdbcBoardRepository.saveBoard(board);
        jdbcBoardRepository.deleteBoard(testId);
        Board findBoard = jdbcBoardRepository.findBoardById(testId);

        //then
        assertThat(findBoard).isNull();
    }
}