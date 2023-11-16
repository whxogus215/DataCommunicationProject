package com.nft.reservation.web.concert;

import com.google.gson.JsonArray;
import com.nft.reservation.domain.image.ImageStore;
import com.nft.reservation.domain.image.UploadImage;
import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.domain.concert.ConcertService;
import com.nft.reservation.web.concert.dto.ConcertForm;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.MalformedURLException;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "콘서트", description = "콘서트 관련 API")
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/concert")
public class ConcertController {

    private final ConcertService concertService;
    private  final ImageStore imageStore;

    @GetMapping("/list")
    public void getList() {
        // 공연 목록 조회        
    }

    @GetMapping("/news")
    public void getListForCarousel() {
        // 캐러샐 및 홈 화면에 조회되는 목록 조회    
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    @Operation(summary = "특정 공연 페이지 상세 조회")
    public ConcertDTO getDetailByID(@PathVariable("id") Long id) {
        // 특정 공연 페이지 상세 조회
        return concertService.getConcertDetail(id);
    }

    @GetMapping("/detail/{id}/book")
    @ResponseBody
    @Operation(summary = "특정 공연의 좌석 페이지 조회")
    public List<SeatDTO> getBookableSeat(@PathVariable("id") Long id) {
        // 예매된 좌석 리스트 조회
        List<SeatDTO> concertSeat = concertService.getConcertSeat(id);

        // 특정 공연의 공연장 크기 조회
        ConcertHallDTO concertHallDTO = concertService.getConcertHallSize(id);

        long rowOfPlace = concertHallDTO.getRowSize();
        long columnOfPlace = concertHallDTO.getColumnSize();

        for (int i = 1; i <= rowOfPlace; i++) {
            for (int j = 1; j <= columnOfPlace; j++) {
                SeatDTO seatDTO = new SeatDTO();

                seatDTO.setRow(i);
                seatDTO.setCol((char) (j + 64));
                seatDTO.setData(true);

                if (!concertSeat.contains(seatDTO)) {
                    seatDTO.setData(false);
                    concertSeat.add(seatDTO);
                }
            }
        }

        // seatDTO 행 -> 열 순으로 정렬 기준 만들기 : Comparator
        concertSeat.sort(Comparator.comparingInt(SeatDTO::getRow)
                .thenComparing(SeatDTO::getCol));
        return concertSeat;
    }

    @PostMapping("/detail/{id}/book")
    @ResponseBody
    public String postBookableSeat(@PathVariable("id") Long id,
                                   @RequestBody List<SeatDTO> seatDTOs) {
        // 특정 공연의 좌석 예매
        // 요청 값(JSON 배열) : [{row : 0, col: 'B'}, {row : 1, col: 'B'}]
        return null;
    }

    // 공연 등록 폼 페이지 조회
    @GetMapping("/new")
    public String getConcertForm() {
        return "concert-form";
    }

    // 공연 등록
    @PostMapping("/new")
    @ResponseBody
    public String addConcert(@ModelAttribute ConcertForm form) {
        concertService.createConcert(form);

        return "OK";
    }

    @GetMapping("/images/{filename}")
    @ResponseBody
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return concertService.getDownloadImage(filename);
    }
}
