package com.nft.board.web.login;

import com.nft.board.domain.login.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "로그인", description = "로그인 관련 API")
@RestController
@Slf4j
public class LoginController {

    @Operation(summary = "login", description = "로그인 요청", responses = {
            @ApiResponse(responseCode = "200", description = "로그인 성공")
    })
    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm, HttpServletRequest request) {

        log.info("id={} pw={}",loginForm.getLoginId(), loginForm.getPassword());

        return "CORS 해결";
    }

    @Operation(summary = "logout", description = "요청 시 세션을 같이 전달하면 로그아웃 처리 됩니다.", responses = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공")
    })
    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }
}
