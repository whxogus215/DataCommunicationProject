package com.nft.reservation.domain.board;

import com.nft.reservation.web.board.BoardDTO;

public interface BoardService {
    public void createBoard(BoardDTO boardDTO);

    public BoardDTO readBoard(BoardDTO boardDTO);

    public void updateBoard(BoardDTO boardDTO);

    public void deleteBoard(BoardDTO boardDTO);

}
