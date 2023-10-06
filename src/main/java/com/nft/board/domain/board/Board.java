package com.nft.board.domain.board;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private int boardId; // 게시글 ID
    private String title; // 게시글 제목
    private String content; // 게시글 내용

    private int price; // 후원 금액

    private Date createdDate; // 작성일
    private Date modifiedDate; // 수정일

    private int adminId; // 운영자(게시글 작성자) ID
    private int imageId; // 첨부된 이미지 ID

}
