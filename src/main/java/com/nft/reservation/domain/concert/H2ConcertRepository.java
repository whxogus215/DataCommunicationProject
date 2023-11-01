package com.nft.reservation.domain.concert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class H2ConcertRepository implements JdbcConcertRepository{

    private final DataSource dataSource;

    @Override
    public Optional<Concert> findById(Integer id) {
        String sql = "select * from concert where concert_id = ?";

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Concert concert = new Concert();

                concert.setId(rs.getInt(1));
                concert.setTitle(rs.getString(2));
                concert.setDate(rs.getDate(3));
                concert.setRunningTime(4);
                concert.setPlace(rs.getString(5));
                concert.setRankId(rs.getInt(6));
                concert.setHallId(rs.getInt(7));

                return Optional.of(concert);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
        return Optional.empty();
    }

    @Override
    public List<Seat> findBookedSeatById(Integer id) {
        String sql = "select * from seat where concert_id = ?";

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            List<Seat> seats = new ArrayList<>();

            while (rs.next()) {
                Seat seat = new Seat();

                seat.setRow(rs.getInt(2));
                seat.setCol(rs.getString(3).charAt(0));
                seat.setData(rs.getBoolean(4));

                seats.add(seat);
            }
            return seats;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(pstmt);

        // 트랜잭션 동기화를 위해 DataSourceUtils 메서드 사용
        DataSourceUtils.releaseConnection(con, dataSource);
    }


}
