package com.nft.reservation.domain.concert.repository;

import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Image;
import com.nft.reservation.domain.concert.entity.Seat;
import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
    public Optional<ConcertDTO> findConcertById(Long id) {
        String sql = "SELECT c.id, c.title, c.date, c.running_time, c.cast_member, " +
                "h.name, h.address, r.detail " +
                "FROM concert AS c " +
                "JOIN hall AS h ON c.hall_id = h.id " +
                "JOIN rank AS r ON c.rank_id = r.id " +
                "WHERE c.id = ?";
        return template.queryForObject(sql, concertDtoRowMapper(), id);
    }

    @Override
    public List<Seat> findBookedSeatById(Long id) {
        String sql = "select * from seat where concert_id = ?";
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
            ps.setLong(2, concertHallDTO.getCapacity());
            ps.setLong(3, concertHallDTO.getRowSize());
            ps.setLong(4, concertHallDTO.getColumnSize());
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
    public Long findRankIdByDetail(String detail) {
        String sql = "SELECT id FROM rank WHERE detail = ?";
        try {
            return template.queryForObject(sql, Long.class, detail);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Long saveImage(Image image) {
        String sql = "INSERT INTO image (upload_name, store_name, concert_id) VALUES (?, ? ,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, image.getUploadName());
            ps.setString(2, image.getStoreName());
            ps.setLong(3, image.getConcertId());
            return ps;
        }, keyHolder);
        long id = keyHolder.getKey().longValue();
        return id;
    }

    @Override
    public List<Image> findImageByConcertId(Long id) {
        String sql = "SELECT upload_name, store_name, concert_id FROM image WHERE concert_id = ?";
        try {
            return template.query(sql, imageRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private RowMapper<Optional<ConcertDTO>> concertDtoRowMapper() {
        return (rs, rowNum) -> {
            ConcertDTO concertDTO = new ConcertDTO();

            concertDTO.setId(rs.getLong(1));
            concertDTO.setTitle(rs.getString(2));
            concertDTO.setDay(rs.getString(3));
            concertDTO.setRunningTime(rs.getLong(4));
            concertDTO.setCastMember(rs.getString(5));
            concertDTO.setHallName(rs.getString(6));
            concertDTO.setHallAddress(rs.getString(7));
            concertDTO.setRate(rs.getString(8));

            return Optional.of(concertDTO);
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
            concertHall.setCapacity(rs.getLong(2));
            concertHall.setRowSize(rs.getLong(3));
            concertHall.setColumnSize(rs.getLong(4));

            return Optional.of(concertHall);
        };
    }

    private RowMapper<Image> imageRowMapper() {
        return (rs, rowNum) -> {
            Image image = new Image();

            image.setUploadName(rs.getString(1));
            image.setStoreName(rs.getString(2));
            image.setConcertId(rs.getLong(3));

            return image;
        };
    }
}
