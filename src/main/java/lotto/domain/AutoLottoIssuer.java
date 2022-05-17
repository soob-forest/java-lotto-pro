package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoIssuer {
    private static final Money LOTTO_PRICE = Money.from(1000);

    private final LottoRandomFactory lottoRandomFactory;

    public AutoLottoIssuer(LottoRandomFactory lottoRandomFactory) {
        this.lottoRandomFactory = lottoRandomFactory;
    }

    public Lottos issue(Money orderPrice) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int index = 0; index < (int) orderPrice.divide(LOTTO_PRICE); index++) {
            lottoList.add(lottoRandomFactory.create());
        }

        return Lottos.from(lottoList);
    }
}
