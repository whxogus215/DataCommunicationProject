package com.nft.reservation.domain.image;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class ImageStoreTest {

    @Autowired
    private ImageStore imageStore;

    @DisplayName("이미지 저장 테스트")
    @Test
    void storeImages() throws IOException {
        String uploadName = "테스트1.png";
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file", uploadName, "image/png", uploadName.getBytes(StandardCharsets.UTF_8));
        List<MultipartFile> multipartFileList = new ArrayList<>();
        multipartFileList.add(multipartFile);

        List<UploadImage> uploadImages = imageStore.storeImages(multipartFileList);

        assertThat(uploadImages.get(0).getUploadFileName()).isEqualTo(uploadName);
    }
}
