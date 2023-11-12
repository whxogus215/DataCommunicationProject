package com.nft.reservation.domain.image;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadImage {
    private String uploadFileName; // 사용자가 설정한 파일 이름
    private String storeFileName; // 실제 서버에 저장되는 파일 이름 (중복 X)

    public UploadImage(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
