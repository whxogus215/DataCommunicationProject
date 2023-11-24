package com.nft.reservation.web.image;

import com.nft.reservation.domain.concert.service.ConcertService;
import java.net.MalformedURLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ConcertService concertService;

    @GetMapping("/images/{filename}")
    @ResponseBody
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        log.info("/images/{filename}으로 GET 요청 받음 : 전달받은 파일이름={}",filename);
        return concertService.getDownloadImage(filename);
    }
}
