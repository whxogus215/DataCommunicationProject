package com.nft.board.domain.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class H2BoardRepository implements JdbcBoardRepository{

    private final DataSource dataSource;

    @Override
    public Board saveBoard(Board board) {
        String sql = "insert into board(title, content, price, created_date) values (?,?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        con = getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setInt(3, board.getPrice());
            pstmt.setDate(4, new Date(new java.util.Date().getTime()));
            // modified_date는 update 시 추가
            /**
             * TO DO
             * admin_id 컬럼 추가하는 기능
             */

            pstmt.executeUpdate();

            return board;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }

    }

    @Override
    public Board findBoardById(int boardId) {
        return null;
    }

    @Override
    public Board updateBoardById(int boardId) {
        return null;
    }

    @Override
    public void deleteBoardById(int boardId) {

    }

    private Connection getConnection() {
        Connection con = DataSourceUtils.getConnection(dataSource);
        return con;
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        // 트랜잭션 동기화를 위해 DataSourceUtils 메서드 사용
        DataSourceUtils.releaseConnection(con, dataSource);
    }

}
