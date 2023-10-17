package com.nft.reservation.domain.login;

import com.nft.reservation.domain.user.admin.Admin;
import com.nft.reservation.domain.user.admin.JdbcAdminRepository;
import com.nft.reservation.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JdbcAdminRepository repository;

    @Override
    public Admin login(LoginForm loginForm) {
        /**
         * 1. 전달받은 폼의 ID와 PW를 갖고와서 login 로직 실행
         * 2.
         */

        return null;



    }
}
