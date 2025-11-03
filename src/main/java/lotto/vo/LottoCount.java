package lotto.vo;

import lotto.common.constants.Constants;
import lotto.common.exception.LottoException;
import lotto.common.message.ErrorMessage;

public record LottoCount(int lottoCount) {
    public LottoCount {
        if (lottoCount <= 0) {
            throw new LottoException(ErrorMessage.LOTTO_COUNT_NOT_POSITIVE);
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
