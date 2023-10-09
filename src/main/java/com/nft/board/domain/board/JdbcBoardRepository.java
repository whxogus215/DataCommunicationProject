package com.nft.board.domain.board;

public interface JdbcBoardRepository {

    // 게시판 작성
    public void saveBoard(Board board);

    // 게시판 글 조회
    public Board findBoardById(int boardId);

    // 게시판 글 수정
    public Board updateBoard(Board updateBoard);

    // 게시판 글 삭제
    public void deleteBoard(int boardId);

}
