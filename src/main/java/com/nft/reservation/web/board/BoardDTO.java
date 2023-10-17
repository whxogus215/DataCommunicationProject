package com.nft.reservation.web.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardDTO {

    private String title; // 게시글 제목
    private String content; // 게시글 내용

    private int price; // 후원 금액

    private MultipartFile imageFile; // 첨부된 이미지 파일

}
