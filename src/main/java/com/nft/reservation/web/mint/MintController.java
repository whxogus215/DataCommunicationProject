package com.nft.reservation.web.mint;

import com.nft.reservation.domain.mint.service.MintService;
import com.nft.reservation.web.mint.dto.MintUrlResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "민팅", description = "민팅 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/mint")
public class MintController {

    private final MintService mintService;

    @GetMapping("images/{id}")
    public MintUrlResponse getMintUrl(@PathVariable("id") Long id) {
        return mintService.getMintImageUrl(id);
    }
}
