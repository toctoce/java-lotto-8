package lotto.service;

import java.util.List;
import java.util.stream.IntStream;
import lotto.common.constants.Constants;
import lotto.vo.lotto.Lotto;
import lotto.vo.lotto.Lottos;
import lotto.vo.LottoCount;
import lotto.util.RandomNumberGenerator;

public class LottoGenerator {
    public Lotto createLotto() {
        List<Integer> lottoNumbers = RandomNumberGenerator.generateNumbers(
                Constants.LOTTO_NUMBER_MIN,
                Constants.LOTTO_NUMBER_MAX,
                Constants.LOTTO_NUMBER_COUNT);

        return new Lotto(lottoNumbers);
    }

    public Lottos createLottos(LottoCount lottoCount) {
        List<Lotto> lottos = IntStream.range(0, lottoCount.lottoCount())
                .mapToObj(i -> createLotto())
                .toList();

        return new Lottos(lottos, lottoCount);
    }
}



