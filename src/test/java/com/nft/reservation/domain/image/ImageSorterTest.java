package com.nft.reservation.domain.image;

import static org.assertj.core.api.Assertions.*;

import com.nft.reservation.domain.concert.repository.JdbcConcertRepository;
import com.nft.reservation.domain.concert.entity.Image;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ImageSorterTest {
    @Autowired
    JdbcConcertRepository repository;

    Long findId;
    List<Image> findImages;

    @BeforeEach
    void before() {
        findId = 101L;
        findImages = repository.findImageByConcertId(findId);
    }

    @Test
    @DisplayName("이미지 업로드명에 썸네일이 들어간 이미지 반환 테스트")
    void getThumbNailTest() {
        Image thumbNail = ImageSorter.getThumbNail(findImages);
        assertThat(thumbNail.getUploadName()).contains("썸네일");
    }

    @Test
    @DisplayName("이미지 업로드명에 캐러셀이 들어간 이미지 반환 테스트")
    void getCarouselTest() {
        Image carousel = ImageSorter.getCarousel(findImages);
        assertThat(carousel.getUploadName()).contains("캐러셀");
    }

    @Test
    @DisplayName("이미지 업로드명에 본문이 들어간 이미지 리스트 반환 테스트")
    @MethodSource("findImages")
    void getContentsTest() {
        List<Image> contents = ImageSorter.getContents(findImages);
        for (Image content : contents) {
            assertThat(content.getUploadName().contains("본문"));
        }
    }
}
