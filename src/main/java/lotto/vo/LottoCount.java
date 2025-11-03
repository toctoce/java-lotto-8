package lotto.vo;

import lotto.constants.Constants;
import lotto.message.ErrorMessage;

public record LottoCount(int lottoCount) {
    public LottoCount {
        if (lottoCount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_COUNT_NOT_POSITIVE.getMessage());
        }
    }

    public static LottoCount of(Budget budget) {
        return new LottoCount(budget.amount() / Constants.LOTTO_PRICE);
    }

    @Override
    public String toString() {
        return String.valueOf(lottoCount);
    }
}
