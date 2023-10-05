package com.nft.board.web.board;

import lombok.Data;

@Data
public class BoardDTO {

    private String title; // 게시글 제목
    private String content; // 게시글 내용

    private int price; // 후원 금액

}
