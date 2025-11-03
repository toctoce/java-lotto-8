package lotto.vo;

import lotto.constants.Constants;
import lotto.message.ErrorMessage;
import lotto.vo.lotto.Lotto;

public record DrawnLottoNumber(Lotto lotto, Integer bonusNumber) {

    public DrawnLottoNumber {
        validateNumber(bonusNumber);
        validateNoDuplicates(lotto, bonusNumber);
    }

    private void validateNumber(Integer number) {
        if (number < Constants.LOTTO_NUMBER_MIN || number > Constants.LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        }
    }

    private void validateNoDuplicates(Lotto numbers, Integer bonusNumber) {
        if (numbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATED.getMessage());
        }
    }
}
