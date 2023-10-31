package com.nft.reservation.web.concert;

import com.nft.reservation.domain.concert.ConcertDTO;
import com.nft.reservation.domain.concert.ConcertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "콘서트", description = "콘서트 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/concert")
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping("/list")
    public void getList() {
        // 공연 목록 조회        
    }
    
    @GetMapping("/news")
    public void getListForCarousel() {
        // 캐러샐 및 홈 화면에 조회되는 목록 조회    
    }
    
    @GetMapping("/detail/{id}")
    public ConcertDTO getDetailByID(@PathVariable("id") Integer id) {
        // 특정 공연 페이지 상세 조회
        return concertService.getConcertDetail(id);
    }
    
}
