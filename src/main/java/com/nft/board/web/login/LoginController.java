package com.nft.board.web.login;

import com.nft.board.domain.login.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;


    @Operation(summary = "login", description = "로그인 요청")
    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm, HttpServletRequest request) {

        log.info("id={} pw={}",loginForm.getLoginId(), loginForm.getPassword());

        return "CORS 해결";
    }

    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }
}
