package com.nft.reservation.web.board;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시판", description = "게시판 관련 API")
@RestController
@RequestMapping("/boards")
public class BoardController {

    @PostMapping(value = "",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BoardDTO createBoard(@ModelAttribute BoardDTO boardDTO) {
        return new BoardDTO();
    }

    @GetMapping("/{boardId}")
    public BoardDTO readBoard(@PathVariable String boardId) {
        return new BoardDTO();
    }

    @PutMapping("/{boardId}")
    public BoardDTO updateBoard(@PathVariable String boardId) {
        return new BoardDTO();
    }

    @DeleteMapping("/{boardId}")
    public HttpStatusCode deleteBoard(@PathVariable String boardId) {
        return HttpStatusCode.valueOf(200);
    }
}
