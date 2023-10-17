package com.nft.reservation.domain.board;

import com.nft.reservation.web.board.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final JdbcBoardRepository jdbcBoardRepository;

    @Override
    public void createBoard(BoardDTO boardDTO) {
        /**
         * Map Struct 라이브러리로 엔티티로 변환 후 서비스 로직 호출
         */
    }

    @Override
    public BoardDTO readBoard(BoardDTO boardDTO) {
        return null;
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) {

    }

    @Override
    public void deleteBoard(BoardDTO boardDTO) {

    }
}
