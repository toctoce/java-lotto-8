package lotto.domain.vo.lotto;

import java.util.List;
import java.util.Objects;
import lotto.domain.constants.Constants;
import lotto.domain.message.ErrorMessage;

// 필드 추가 금지!!
public class Lotto {
    private final List<Integer> numbers; // private 변경 금지

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_COUNT.getMessage());
        }

        numbers.forEach(this::validateNumber);
    }

    private void validateNumber(Integer number) {
        if (number < Constants.LOTTO_NUMBER_MIN || number > Constants.LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }
    // TODO: 추가 기능 구현

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(getNumbers(), lotto.getNumbers());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNumbers());
    }
}
