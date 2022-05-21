package lotto.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchResultTest {


    @ParameterizedTest
    @CsvSource(value = {"0:false:0", "1:false:0", "2:false:0", "3:false:5000", "4:false:50000", "5:false:1500000",
            "5:true:30000000", "6:false:2000000000"}, delimiter = ':')
    void 당첨번호와_일치하는_숫자_개수를_이용해_매치결과_생성(int matchCount, boolean isBonus, double money) {

        assertThat(MatchResult.of(matchCount, isBonus).getCashPrize()).isEqualTo(Money.from(money));
    }

    @ParameterizedTest
    @CsvSource(value = {"0:ture:0", "1:true:0", "2:true:0", "3:true:5000", "4:true:50000", "5:false:1500000",
            "5:true:30000000", "6:true:2000000000"}, delimiter = ':')
    void 당첨번호와_일치하는_숫자_개수와_보너스를_이용해_매치결과_생성(int matchCount, boolean inBonus, double money) {

        assertThat(MatchResult.of(matchCount, inBonus).getCashPrize()).isEqualTo(Money.from(money));
    }

    @Test
    void 당첨번호와_일치하는_숫자_개수_예외() {
        int input = 7;
        assertThatThrownBy(() -> MatchResult.of(input, false)).isInstanceOf(IllegalArgumentException.class);
    }
}