package lotto.common.exception;

import lotto.common.message.ErrorMessage;

public class LottoException extends IllegalArgumentException {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public LottoException(ErrorMessage errorMessage) {
        super(formatMessage(errorMessage.getMessage()));
    }

    private static String formatMessage(String message) {
        return ERROR_PREFIX + message;
    }
}