package lotto.service.lotto;

import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.constants.Constants;
import lotto.domain.vo.lotto.Lotto;
import lotto.domain.vo.lotto.Lottos;
import lotto.domain.vo.LottoCount;
import lotto.util.RandomNumberGenerator;

public class LottoGenerator {
    public static Lotto createLotto() {
        List<Integer> lottoNumbers = RandomNumberGenerator.generateNumbers(
                Constants.LOTTO_NUMBER_MIN,
                Constants.LOTTO_NUMBER_MAX,
                Constants.LOTTO_NUMBER_COUNT);

        return new Lotto(lottoNumbers);
    }

    public static Lottos createLottos(LottoCount lottoCount) {

        List<Lotto> lottos = IntStream.range(0, lottoCount.lottoCount())
                .mapToObj(i -> createLotto())
                .toList();

        return new Lottos(lottos);
    }
}



