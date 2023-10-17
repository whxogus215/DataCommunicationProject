package com.nft.reservation.web.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "로그인 요청 폼")
@Data
@NoArgsConstructor
public class LoginForm {

    @Schema(description = "로그인 ID", example = "test")
    private String loginId;

    @Schema(description = "로그인 Password", example = "test!")
    private String password;
}
