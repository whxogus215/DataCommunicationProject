package com.nft.reservation.domain.image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<UploadImage> storeImages(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadImage> uploadImages = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFiles.isEmpty()) {
                uploadImages.add(storeImage(multipartFile));
            }
        }
        return uploadImages;
    }

    private UploadImage storeImage(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originalImageName = multipartFile.getOriginalFilename();
        /** 서버에 저장되는 UUID 파일이름으로 만들기
         * 1. 파일 확장자 분리
         * 2. 생성한 UUID와 파일 확장자를 더한 서버 저장 파일 이름 반환
         */
        String storeImageName = createStoreImage(originalImageName);
        System.out.println(fileDir);
        System.out.println(storeImageName);
        System.out.println(getFullPath(storeImageName));
        multipartFile.transferTo(new File(getFullPath(storeImageName)));
        return new UploadImage(originalImageName, storeImageName);
    }

    private String createStoreImage(String originalName) {
        String ext = extractExt(originalName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalName) {
        int pos = originalName.lastIndexOf(".");
        return originalName.substring(pos + 1);
    }
}
