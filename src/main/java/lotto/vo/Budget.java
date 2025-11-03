package lotto.vo;

import lotto.constants.Constants;
import lotto.message.ErrorMessage;

public record Budget(int amount) {
    public Budget {
        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_NOT_POSITIVE.getMessage());
        }
        if (amount % Constants.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    ErrorMessage.AMOUNT_NOT_MULTIPLE_OF_LOTTO_PRICE
                            .getMessage(Integer.toString(Constants.LOTTO_PRICE)));
        }
    }
}
