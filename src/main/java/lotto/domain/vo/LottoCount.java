package lotto.domain.vo;

import lotto.domain.constants.Constants;
import lotto.domain.message.ErrorMessage;

public record LottoCount(int lottoCount) {
    public LottoCount {
        if (lottoCount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_COUNT_NOT_POSITIVE.getMessage());
        }
    }

    public static LottoCount of(Budget budget) {
        return new LottoCount(budget.amount() / Constants.LOTTO_PRICE);
    }
}
