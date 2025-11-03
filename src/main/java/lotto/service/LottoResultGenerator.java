package lotto.service;

import java.util.List;
import lotto.vo.Budget;
import lotto.vo.DrawnLottoNumber;
import lotto.vo.lotto.Lotto;
import lotto.vo.lotto.Lottos;
import lotto.vo.lottoresult.LottoRank;
import lotto.vo.lottoresult.LottoResult;
import lotto.vo.lottoresult.LottoResults;

public class LottoResultGenerator {
    public LottoResults createLottoResults(Budget budget, Lottos lottos, DrawnLottoNumber drawnLottoNumber) {
        List<LottoResult> lottoResults = lottos.lottos()
                .stream()
                .map((Lotto lotto) -> createLottoResult(lotto, drawnLottoNumber))
                .toList();

        return new LottoResults(budget, lottoResults);
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
}
