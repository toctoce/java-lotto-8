package lotto.service;

import java.util.List;
import lotto.vo.Budget;
import lotto.vo.DrawnLotto;
import lotto.vo.lotto.Lotto;
import lotto.vo.lotto.Lottos;
import lotto.vo.lottoresult.LottoRank;
import lotto.vo.lottoresult.LottoResult;
import lotto.vo.lottoresult.LottoResults;

public class LottoResultGenerator {
    public LottoResults createLottoResults(Budget budget, Lottos lottos, DrawnLotto drawnLotto) {
        List<LottoResult> lottoResults = lottos.lottos()
                .stream()
                .map((Lotto lotto) -> createLottoResult(lotto, drawnLotto))
                .toList();

        return new LottoResults(budget, lottoResults);
    }

    private LottoResult createLottoResult(Lotto lotto, DrawnLotto drawnLotto) {
        LottoRank rank = calculateLottoRank(lotto, drawnLotto);
        return new LottoResult(rank);
    }

    private LottoRank calculateLottoRank(Lotto lotto, DrawnLotto drawnLotto) {
        int matchCount = lotto.countMatches(drawnLotto.lotto());
        boolean matchBonus = lotto.containsNumber(drawnLotto.bonusNumber());

        return LottoRank.valueOf(matchCount, matchBonus);
    }
}
