package com.nft.reservation.domain.utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TokenCreatorTest {

    @ParameterizedTest
    @CsvSource({"1", "36", "45", "58"})
    @DisplayName("민팅토큰 생성 테스트")
    void createMintToken(Long mintCount) {
        String startFormat = "0x";
        String wrongStartFormat = "0x0";

        String createdTokenValue = TokenCreator.createMintToken(mintCount);

        assertThat(createdTokenValue).startsWith(startFormat);
        assertThat(createdTokenValue).doesNotStartWith(wrongStartFormat);
    }
}
