package com.nft.board.domain.login;

import com.nft.board.domain.login.LoginService;
import com.nft.board.domain.user.admin.H2JdbcAdminRepository;
import com.nft.board.domain.user.admin.JdbcAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JdbcAdminRepository repository;

    @Override
    public void login(String loginId, String loginPassword) {
        /**
         * 1. 저장소에서 입력받은 ID에 대한  PASSWORD를 가져온다.
         * 2.
         */



    }
}
