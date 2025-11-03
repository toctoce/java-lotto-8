package lotto.view;

import lotto.domain.message.ViewMessage;
import lotto.domain.vo.LottoCount;
import lotto.domain.vo.lotto.Lottos;

public class OutputView {
    public void writeLottoCount(LottoCount lottoCount) {
        System.out.println(ViewMessage.LOTTO_COUNT_OUTPUT_MESSAGE.getMessage(lottoCount.toString())
                + ViewMessage.NEW_LINE.getMessage());
    }

    public void writeLottos(Lottos lottos) {
        System.out.println(lottos);
    }

    // TODO : 파라미터로 결과 출력.
    public void writeDrawLottoResult() {
        System.out.println(ViewMessage.DRAW_LOTTO_RESULT_OUTPUT_MESSAGE.getMessage());
        System.out.println(ViewMessage.DIVIDER.getMessage());
//        System.out.println(ViewMessage.MATCH_3_MESSAGE.getMessage(), match3PrizeMoney, match3Count);
//        System.out.println(ViewMessage.MATCH_4_MESSAGE.getMessage(), match4PrizeMoney, match4Count);
//        System.out.println(ViewMessage.MATCH_5_MESSAGE.getMessage(), match5PrizeMoney, match5Count);
//        System.out.println(ViewMessage.MATCH_5_AND_BONUS_MESSAGE.getMessage(), match5AndBonusPrizeMoney, match5AndBonusCount);
//        System.out.println(ViewMessage.MATCH_6_MESSAGE.getMessage(), match6PrizeMoney, match6Count);
    }

    // TODO : 파라미터를 dto로 수정해야함.
    public void writeRateOfReturn(Double rateOfReturn) {
        System.out.println(ViewMessage.RATE_OF_RETURN_OUTPUT_MESSAGE.getMessage(rateOfReturn.toString()));
    }
}
