package lotto.vo;

import lotto.constants.Constants;
import lotto.exception.LottoException;
import lotto.message.ErrorMessage;
import lotto.vo.lotto.Lotto;

public record DrawnLottoNumber(Lotto lotto, Integer bonusNumber) {

    public DrawnLottoNumber {
        validateNumber(bonusNumber);
        validateNoDuplicates(lotto, bonusNumber);
    }

    private void validateNumber(Integer number) {
        if (number < Constants.LOTTO_NUMBER_MIN || number > Constants.LOTTO_NUMBER_MAX) {
            throw new LottoException(ErrorMessage.INVALID_NUMBER_RANGE);
        }
    }

    private void validateNoDuplicates(Lotto numbers, Integer bonusNumber) {
        if (numbers.getNumbers().contains(bonusNumber)) {
            throw new LottoException(ErrorMessage.BONUS_NUMBER_DUPLICATED);
        }
    }
}
