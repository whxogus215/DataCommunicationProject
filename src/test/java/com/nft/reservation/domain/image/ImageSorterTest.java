package com.nft.reservation.domain.image;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.nft.reservation.domain.concert.JdbcConcertRepository;
import com.nft.reservation.domain.concert.entity.Image;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageSorterTest {
    @Autowired
    JdbcConcertRepository repository;

    @Test
    @DisplayName("이미지 업로드명에 썸네일이 들어간 이미지 반환 테스트")
    void getThumbNailTest() {
        Long findId = 101L;

        List<Image> findImages = repository.findImageByConcertId(findId);

        Image thumbNail = ImageSorter.getThumbNail(findImages);
        assertThat(thumbNail.getUploadName()).contains("썸네일");
    }
}
