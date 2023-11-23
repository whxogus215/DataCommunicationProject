package com.nft.reservation.domain.utils;

public class TokenCreator {
    public static String createMintToken(Long mintCount) {
        return "0x" + Long.toHexString(mintCount);
    }
}
