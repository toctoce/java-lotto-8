package lotto.util;

import lotto.domain.message.ErrorMessage;
import lotto.domain.vo.Budget;

public class Parser {
    public Budget InputToBudget(String input) {
        validateNotBlank(input);
        validateInteger(input);
        int amount = Integer.parseInt(input);
        return new Budget(amount);
    }

    private void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_A_NUMBER.getMessage());
        }
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_REQUIRED.getMessage());
        }
    }
}
