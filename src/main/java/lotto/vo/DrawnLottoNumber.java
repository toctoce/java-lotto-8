package lotto.vo;

import lotto.common.constants.Constants;
import lotto.common.exception.LottoException;
import lotto.common.message.ErrorMessage;
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
