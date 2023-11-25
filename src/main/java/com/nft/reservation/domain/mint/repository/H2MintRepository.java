package com.nft.reservation.domain.mint.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class H2MintRepository implements JdbcMintRepository {

    private final JdbcTemplate template;

    @Override
    public void saveMintImageUrl(String url, Long concertId) {
        String sql = "insert into mint_image (image_url, concert_id) values (?, ?)";
        template.update(sql, url, concertId);
    }

    @Override
    public Optional<String> getMintImageUrl(Long concertId) {
        String sql = "select image_url from mint_image where concert_id = ?";
        return Optional.ofNullable(template.queryForObject(sql, String.class ,concertId));
    }
}
