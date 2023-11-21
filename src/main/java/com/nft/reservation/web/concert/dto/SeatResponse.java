package com.nft.reservation.web.concert.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatResponse {
    List<SeatDTO> reservedSeats;
    String createdToken;
}
