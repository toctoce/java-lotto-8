package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.message.ViewMessage;
import lotto.domain.vo.LottoCount;
import lotto.domain.vo.lotto.Lotto;
import lotto.domain.vo.lotto.Lottos;
import lotto.domain.vo.lottoresult.LottoRank;
import lotto.domain.vo.lottoresult.LottoResults;

public class OutputView {

    private final String DIVIDER = "---";
    private final String NEW_LINE = "\n";

    public void writePurchaseHistory(Lottos lottos) {
        writeLottoCount(lottos.lottoCount());
        writeLottos(lottos.lottos());
    }

    private void writeLottoCount(LottoCount lottoCount) {
        System.out.println(ViewMessage.LOTTO_COUNT_OUTPUT_MESSAGE.getMessage(lottoCount.toString())
                + NEW_LINE);
    }

    private void writeLottos(List<Lotto> lottos) {
        System.out.println(lottos);
    }

    public void writeDrawLottoResult(LottoResults lottoResults) {
        System.out.println(ViewMessage.DRAW_LOTTO_RESULT_OUTPUT_MESSAGE.getMessage());
        System.out.println(DIVIDER);
        System.out.println(resultFormat(lottoResults.getRankCount()));
        System.out.println(rateOfReturnFormat(lottoResults.getRateOfReturn()));
    }

    private String rateOfReturnFormat(double rateOfReturn) {
        String formattedRate = String.format("%.1f", rateOfReturn);
        return ViewMessage.RATE_OF_RETURN_OUTPUT_MESSAGE.getMessage(formattedRate);
    }

    private String resultFormat(Map<LottoRank, Integer> rankCount) {
        return ViewMessage.FIFTH_MESSAGE
                        .getMessage(String.valueOf(rankCount.get(LottoRank.FIFTH))) +
                NEW_LINE +
                ViewMessage.FOURTH_MESSAGE
                        .getMessage(String.valueOf(rankCount.get(LottoRank.FOURTH))) +
                NEW_LINE +
                ViewMessage.THIRD_MESSAGE
                        .getMessage(String.valueOf(rankCount.get(LottoRank.THIRD))) +
                NEW_LINE +
                ViewMessage.SECOND_MESSAGE
                        .getMessage(String.valueOf(rankCount.get(LottoRank.SECOND))) +
                NEW_LINE +
                ViewMessage.FIRST_MESSAGE
                        .getMessage(String.valueOf(rankCount.get(LottoRank.FIRST)));
    }
}
