package lotto.domain.message;

import lotto.domain.constants.Constants;

// TODO: 에러 구현해서 [ERROR] 문자열 제거
public enum ErrorMessage {
    NOT_A_NUMBER("[ERROR] 숫자만 입력해주세요."),
    INPUT_REQUIRED("[ERROR] 값을 반드시 입력해야 합니다."),
    INVALID_INPUT_FORMAT("[ERROR] 입력 형식이 올바르지 않습니다."),

    AMOUNT_NOT_POSITIVE("[ERROR] 금액은 양수여야 합니다."),
    AMOUNT_NOT_MULTIPLE_OF_LOTTO_PRICE("[ERROR] 금액이 %s의 배수가 아닙니다", true),

    INVALID_NUMBER_COUNT("[ERROR] 로또 번호는 " + Constants.LOTTO_NUMBER_COUNT + "개여야 합니다."),
    INVALID_NUMBER_RANGE("[ERROR] 번호가 " + Constants.LOTTO_NUMBER_MIN + "이상 "
            + Constants.LOTTO_NUMBER_MAX + "이하여야 합니다."),

    LOTTO_COUNT_NOT_POSITIVE("[ERROR] 로또 개수는 양수여야 합니다."),
    ;
    private final String message;
    private final boolean isFormatted;

    ErrorMessage(String message) {
        this.message = message;
        this.isFormatted = false;
    }

    ErrorMessage(String message, boolean isFormatted) {
        this.message = message;
        this.isFormatted = isFormatted;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(String... arguments) {
        if (isFormatted) {
            return String.format(message, arguments);
        }
        return message;
    }
}
