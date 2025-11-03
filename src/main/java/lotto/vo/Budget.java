package lotto.vo;

import lotto.common.constants.Constants;
import lotto.common.exception.LottoException;
import lotto.common.message.ErrorMessage;

public record Budget(int amount) {
    public Budget {
        if (amount <= 0) {
            throw new LottoException(ErrorMessage.AMOUNT_NOT_POSITIVE);
        }

        if (amount % Constants.LOTTO_PRICE != 0) {
            throw new LottoException(ErrorMessage.AMOUNT_NOT_MULTIPLE_OF_LOTTO_PRICE);
        }
    }
}
