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
    public void saveBoard(Board board) {
        String sql = "insert into board(title, content, price, created_date) values (?,?,?,?)";

        Connection con = getConnection();;
        PreparedStatement pstmt = null;

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    @Override
    public Board findBoardById(int boardId) {

        String sql = "select * from board where board_id = ?";

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Board board = new Board();

                board.setBoardId(rs.getInt(1));
                board.setTitle(rs.getString(2));
                board.setContent(rs.getString(3));
                board.setPrice(rs.getInt(4));
                board.setCreatedDate(rs.getDate(5));
                board.setModifiedDate(rs.getDate(6));

                return board;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }

        return null;
    }

    @Override
    public Board updateBoard(Board updateBoard) {

        String sql = "update board" +
                " set title = ?, content = ?, price = ?" +
                    ", modified_date = ?" +
                        "where board_id = ?";

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        /**
         * 서비스 계층에서 전달한 Board 객체의 프로퍼티를 가져와서 변경
         */
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, updateBoard.getTitle());
            pstmt.setString(2, updateBoard.getContent());
            pstmt.setInt(3, updateBoard.getPrice());
            pstmt.setDate(4, new Date(new java.util.Date().getTime()));
            pstmt.setInt(5, updateBoard.getBoardId());

            pstmt.executeUpdate();

            return updateBoard;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    @Override
    public void deleteBoard(int boardId) {
        String sql = "delete from board where board_id =?";

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
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
