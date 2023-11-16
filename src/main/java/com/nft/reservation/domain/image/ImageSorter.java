package com.nft.reservation.domain.image;

import com.nft.reservation.domain.concert.entity.Image;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ImageSorter {
    private static final String THUMB_NAIL = "썸네일";
    private static final String CAROUSEL = "캐러셀";
    private static final String CONTENT = "본문";

    // 썸네일 이미지 반환
    // 캐러셀 이미지 반환
    // 본문 이미지 리스트로 반환
    public static Image getThumbNail(List<Image> images) {
        return images.stream()
                .filter(image -> image.getUploadName().contains(THUMB_NAIL))
                .findAny()
                .orElse(null);
    }

    public static Image getCarousel(List<Image> images) {
        return images.stream()
                .filter(image -> image.getUploadName().contains(CAROUSEL))
                .findAny()
                .orElse(null);
    }

    public static List<Image> getContents(List<Image> images) {
        return images.stream()
                .filter(image -> image.getUploadName().contains(CONTENT))
                .sorted(Comparator.comparing(Image::getUploadName))
                .collect(Collectors.toList());
    }
}
