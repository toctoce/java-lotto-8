package lotto.vo;

import lotto.constants.Constants;
import lotto.exception.LottoException;
import lotto.message.ErrorMessage;

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
