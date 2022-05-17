package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTest {
    private Set<LottoNumber> prizeNumbers;
    private int NUMBER_COUNT;

    @BeforeEach
    void setUp() {
        NUMBER_COUNT = 6;
        prizeNumbers = new HashSet<>();
        prizeNumbers.add(LottoNumber.from(1));
        prizeNumbers.add(LottoNumber.from(2));
        prizeNumbers.add(LottoNumber.from(3));
        prizeNumbers.add(LottoNumber.from(4));
        prizeNumbers.add(LottoNumber.from(5));
        prizeNumbers.add(LottoNumber.from(6));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1:2:3:4:5:6:6",
            "1:2:3:4:5:7:5",
            "1:2:3:4:7:8:4",
            "1:2:3:7:8:9:3",
            "1:2:7:8:9:10:2",
            "1:7:8:9:10:11:1",
            "7:8:9:10:11:12:0"},
            delimiter = ':')
    void 매치_결과(int input0, int input1, int input2, int input3, int input4, int input5, int matchCount) {
        LottoNumber[] lottoNumbers = getLottoNumbers(input0, input1, input2, input3, input4, input5);

        Lotto lotto = new Lotto(lottoNumbers);
        MatchResult matchResult = lotto.match(prizeNumbers);
        assertThat(matchResult).isEqualTo(MatchResult.from(matchCount));
    }

    @Test
    void 중복_숫자_예외() {
        assertThatThrownBy(() -> new Lotto(getLottoNumbers(1, 1, 2, 3, 4, 5))).isInstanceOf(
                IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2:3:4:5:6:7:7"}, delimiter = ':')
    void 숫자_개수_예외(int input0, int input1, int input2, int input3, int input4, int input5, int numberCount) {

        LottoNumber[] input = new LottoNumber[NUMBER_COUNT + 1];
        input[0] = LottoNumber.from(input0);
        input[1] = LottoNumber.from(input1);
        input[2] = LottoNumber.from(input2);
        input[3] = LottoNumber.from(input3);
        input[4] = LottoNumber.from(input4);
        input[5] = LottoNumber.from(input5);
        input[6] = LottoNumber.from(numberCount);

        assertThatThrownBy(() -> new Lotto(input)).isInstanceOf(
                IllegalArgumentException.class);
    }

    private LottoNumber[] getLottoNumbers(int input0, int input1, int input2, int input3, int input4, int input5) {
        LottoNumber[] lottoNumbers = new LottoNumber[NUMBER_COUNT];
        lottoNumbers[0] = LottoNumber.from(input0);
        lottoNumbers[1] = LottoNumber.from(input1);
        lottoNumbers[2] = LottoNumber.from(input2);
        lottoNumbers[3] = LottoNumber.from(input3);
        lottoNumbers[4] = LottoNumber.from(input4);
        lottoNumbers[5] = LottoNumber.from(input5);
        return lottoNumbers;
    }

}