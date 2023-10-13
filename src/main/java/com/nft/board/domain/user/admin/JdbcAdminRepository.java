package com.nft.board.domain.user.admin;

import java.util.Optional;

public interface JdbcAdminRepository {

    /*
    1. ID로 Admin 엔티티 가져오기 (select + where) : Admin 객체 or null 반환
     */
    Optional<Admin> findAdminById(String Id);
}
