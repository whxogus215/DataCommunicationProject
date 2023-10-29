package com.nft.reservation.web.concert;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concert")
public class ConcertController {

    @GetMapping("/list")
    public void getList() {
        // 공연 목록 조회        
    }
    
    @GetMapping("/news")
    public void getListForCarousel() {
        // 캐러샐 및 홈 화면에 조회되는 목록 조회    
    }
    
    @GetMapping("/detail/{concert_id}")
    public void getDetailByID() {
        // 특정 공연 페이지 상세 조회
    }
    
}
