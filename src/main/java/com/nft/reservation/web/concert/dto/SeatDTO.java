package com.nft.reservation.web.concert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {
    private Integer row;
    private Character col;

    // 좌석 예매 여부 : true / false
    @JsonProperty("isBooked")
    private boolean booked;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        SeatDTO seatDTO = (SeatDTO) object;
        return booked == seatDTO.booked && Objects.equals(row, seatDTO.row)
                && Objects.equals(col, seatDTO.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, booked);
    }
}
