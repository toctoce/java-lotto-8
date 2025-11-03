package lotto.service.lotto;

import java.util.List;
import lotto.domain.vo.DrawnLottoNumber;
import lotto.domain.vo.lotto.Lotto;
import lotto.domain.vo.lotto.Lottos;
import lotto.domain.vo.lottoresult.LottoRank;
import lotto.domain.vo.lottoresult.LottoResult;
import lotto.domain.vo.lottoresult.LottoResults;

public class LottoResultGenerator {

    public LottoResults createLottoResults(Lottos lottos, DrawnLottoNumber drawnLottoNumber) {
        List<LottoResult> lottoResults = lottos.lottos()
                .stream()
                .map((Lotto lotto) -> createLottoResult(lotto, drawnLottoNumber))
                .toList();

        return LottoResults.of(lottoResults);
    }

    private LottoResult createLottoResult(Lotto lotto, DrawnLottoNumber drawnLottoNumber) {
        LottoRank rank = calculateLottoRank(lotto, drawnLottoNumber);
        return new LottoResult(rank);
    }

    private LottoRank calculateLottoRank(Lotto lotto, DrawnLottoNumber drawnLottoNumber) {
        int matchCount = lotto.countMatches(drawnLottoNumber.lotto());
        boolean matchBonus = lotto.containsNumber(drawnLottoNumber.bonusNumber());

        return LottoRank.valueOf(matchCount, matchBonus);
    }

//    수익률 계산
//    public Lotto
}
