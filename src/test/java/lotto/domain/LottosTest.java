package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottosTest {

    private WinningLotto winningNumbers;
    private Lottos lottos;

    @BeforeEach
    void setUp() {
        int[] winningInputs = {1, 2, 3, 4, 5, 6};
        LottoNumber bonusNumber = LottoNumber.from(7);
        winningNumbers = new WinningLotto(createLottoNumbers(winningInputs), bonusNumber);

        List<Lotto> lottoList = new ArrayList<>();

        lottoList.add(createLotto(new int[]{1, 2, 3, 4, 5, 6}));
        lottoList.add(createLotto(new int[]{1, 2, 3, 4, 5, 7}));
        lottoList.add(createLotto(new int[]{1, 2, 3, 4, 5, 8}));
        lottoList.add(createLotto(new int[]{1, 2, 3, 4, 8, 9}));
        lottoList.add(createLotto(new int[]{1, 2, 3, 8, 9, 10}));

        lottos = Lottos.from(lottoList);
    }

    private LottoNumber[] createLottoNumbers(int[] inputs) {
        LottoNumber[] lottoNumbers = new LottoNumber[inputs.length];

        for (int index = 0; index < inputs.length; index++) {
            lottoNumbers[index] = LottoNumber.from(inputs[index]);
        }

        return lottoNumbers;
    }

    @Test
    void 셋업에_맞는_당첨_로또_리스트_반환() {
        assertAll(
                () -> assertThat(lottos.matchedLottos(winningNumbers, MatchResult.FIRST).size()).isEqualTo(1),
                () -> assertThat(lottos.matchedLottos(winningNumbers, MatchResult.SECOND).size()).isEqualTo(1),
                () -> assertThat(lottos.matchedLottos(winningNumbers, MatchResult.THIRD).size()).isEqualTo(1),
                () -> assertThat(lottos.matchedLottos(winningNumbers, MatchResult.FOURTH).size()).isEqualTo(1),
                () -> assertThat(lottos.matchedLottos(winningNumbers, MatchResult.FIFTH).size()).isEqualTo(1)
        );
    }

    @Test
    void 로또_총_금액() {
        assertThat(lottos.totalPrice()).isEqualTo(Money.from(5000));

    }

    private Lotto createLotto(int[] inputs) {
        LottoNumber[] lottoNumbers = new LottoNumber[inputs.length];

        for (int index = 0; index < inputs.length; index++) {
            lottoNumbers[index] = LottoNumber.from(inputs[index]);
        }

        return new Lotto(lottoNumbers);
    }
}