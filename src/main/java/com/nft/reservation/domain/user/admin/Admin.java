package com.nft.reservation.domain.user.admin;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private Long adminId;
    private String adminLoginid;
    private String adminName;
    private String adminPassword;
    private Date createdDate;
    private Date modifiedDate;

    public Admin(String adminLoginid, String adminPassword) {
        this.adminLoginid = adminLoginid;
        this.adminPassword = adminPassword;
    }
}
