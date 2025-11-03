package lotto.domain.vo;

import lotto.domain.constants.Constants;
import lotto.domain.message.ErrorMessage;
import lotto.domain.vo.lotto.Lotto;

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
