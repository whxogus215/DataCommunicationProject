package com.nft.reservation.domain.concert;

import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Seat;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class H2ConcertRepository implements JdbcConcertRepository {

    private final JdbcTemplate template;

    @Override
    public Long saveConcert(Concert concert) {
        String sql = "INSERT INTO CONCERT " +
                "(title, date, running_time, cast_member, rank_id, hall_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, concert.getTitle());
            ps.setString(2, concert.getDay());
            ps.setLong(3, concert.getRunningTime());
            ps.setString(4, concert.getCastMember());
            ps.setLong(5, concert.getRankId());
            ps.setLong(6, concert.getHallId());
            return ps;
        }, keyHolder);
        long id = keyHolder.getKey().longValue();
        return id;
    }

    @Override
    public Optional<Concert> findById(Integer id) {
        String sql = "select * from concert where id = ?";
        return template.queryForObject(sql, concertRowMapper(), id);
    }

    @Override
    public List<Seat> findBookedSeatById(Integer id) {
        String sql = "select * from seat where id = ?";
        return template.queryForObject(sql, seatRowMapper(), id);
    }

    @Override
    public Long saveHall(ConcertHallDTO concertHallDTO) {
        String sql = "INSERT INTO HALL (name, capacity, roww, column, address) " +
                "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, concertHallDTO.getName());
            ps.setInt(2, concertHallDTO.getCapacity());
            ps.setInt(3, concertHallDTO.getRowSize());
            ps.setInt(4, concertHallDTO.getColumnSize());
            ps.setString(5, concertHallDTO.getAddress());
            return ps;
        }, keyHolder);
        long id = keyHolder.getKey().longValue();
        return id;
    }

    @Override
    public Long findConcertHallIdByName(String name) {
        String sql = "SELECT h.id FROM hall AS h WHERE h.name = ?";
        try {
            return template.queryForObject(sql, Long.class, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Optional<ConcertHall> findConcertHallById(Long id) {
        String sql = "SELECT h.name, h.capacity, h.roww, h.column " +
                "FROM concert AS c " +
                "LEFT JOIN hall AS h ON c.hall_id = h.id " +
                "WHERE c.id = ?";
        try {
            return template.queryForObject(sql, concertHallRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Long saveRank(String detail) {
        String sql = "INSERT INTO RANK (detail) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, detail);
            return ps;
        }, keyHolder);
        long id = keyHolder.getKey().longValue();
        return id;
    }

    @Override
    public Long findRankIdByName(String name) {
        return null;
    }

    private RowMapper<Optional<Concert>> concertRowMapper() {
        return (rs, rowNum) -> {
            Concert concert = new Concert();

            concert.setId(rs.getLong(1));
            concert.setTitle(rs.getString(2));
            concert.setDay(rs.getString(3));
            concert.setRunningTime(rs.getLong(4));
            concert.setCastMember(rs.getString(5));
            concert.setRankId(rs.getLong(6));
            concert.setHallId(rs.getLong(7));

            return Optional.of(concert);
        };
    }

    private RowMapper<List<Seat>> seatRowMapper() {
        return (rs, rowNum) -> {
            List<Seat> seats = new ArrayList<>();

            Seat seat = new Seat();

            seat.setRow(rs.getInt(2));
            seat.setCol(rs.getString(3).charAt(0));
            seat.setData(rs.getBoolean(4));

            seats.add(seat);

            return seats;
        };
    }

    private RowMapper<Optional<ConcertHall>> concertHallRowMapper() {
        return (rs, rowNum) -> {
            ConcertHall concertHall = new ConcertHall();

            concertHall.setName(rs.getString(1));
            concertHall.setCapacity(rs.getInt(2));
            concertHall.setRowSize(rs.getInt(3));
            concertHall.setColumnSize(rs.getInt(4));

            return Optional.of(concertHall);
        };
    }
}
