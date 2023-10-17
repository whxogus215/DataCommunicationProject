package com.nft.reservation.domain.login;

import com.nft.reservation.domain.user.admin.Admin;
import com.nft.reservation.web.login.LoginForm;

public interface LoginService {
    public Admin login(LoginForm loginForm);
}
