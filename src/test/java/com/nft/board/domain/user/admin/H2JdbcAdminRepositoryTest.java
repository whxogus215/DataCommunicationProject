package com.nft.board.domain.user.admin;

import com.nft.board.domain.board.H2BoardRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class H2JdbcAdminRepositoryTest {

    private String url = "jdbc:h2:tcp://localhost/~/test";

    private String username = "sa";

    private String password = "";

    JdbcAdminRepository adminRepository;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        adminRepository = new H2JdbcAdminRepository(dataSource);
    }

    @Test
    @DisplayName("회원 ID 조회 성공")
    void findByIdTest() {
        String testId = "test"; // H2 DB에 등록된 테스트 ID

        Optional<Admin> findAdmin = adminRepository.findAdminById(testId);

        // 조회한 Admin이 null이 아니어야 성공
        assertThat(findAdmin).isNotNull();

        // 조회한 Admin의 로그인 ID가 testId와 일치해야 성공
        assertThat(findAdmin.get().getAdminLoginid()).isEqualTo(testId);
    }

    @Test
    @DisplayName("회원 ID 조회 예외")
    void findByWrongIdTest() {
        String testId = "wrong test"; // H2 DB에 등록되지 않은 잘못 입력된 테스트 ID

        Optional<Admin> findAdmin = adminRepository.findAdminById(testId);

        // 조회한 Admin이 Null이어야 성공
        
        // Optional.empty인 값을 get() 했을 때, 예외가 발생하면 성공
        assertThatThrownBy(() -> findAdmin.get())
                .isInstanceOf(NoSuchElementException.class);
        
        // Optional 객체가 empty인지 확인하는 메서드
        assertThat(findAdmin).isEmpty();
    }
}