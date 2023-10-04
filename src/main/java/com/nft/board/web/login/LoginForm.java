package com.nft.board.web.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginForm {

    @Schema(description = "로그인 ID", example = "로그인 ID 입니다.")
    private String loginId;

    @Schema(description = "로그인 Password", example = "로그인 Password 입니다.")
    private String password;
}
