package com.nft.reservation.web.concert.dto;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {
    private Integer row;
    private Character col;

    // 좌석 예매 여부 : true / false
    private boolean data;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        SeatDTO seatDTO = (SeatDTO) object;
        return data == seatDTO.data && Objects.equals(row, seatDTO.row)
                && Objects.equals(col, seatDTO.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, data);
    }
}
