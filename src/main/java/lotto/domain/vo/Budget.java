package lotto.domain.vo;

import lotto.domain.constants.Constants;
import lotto.domain.message.ErrorMessage;

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

    public int calculateLottoCount() {
        return amount / Constants.LOTTO_PRICE;
    }
}
