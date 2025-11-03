package lotto.common.message;

import lotto.common.constants.Constants;

public enum ErrorMessage {
    NOT_A_NUMBER("숫자만 입력해주세요."),
    NOT_AN_INTEGER("정수로 변환할 수 없습니다."),
    INPUT_REQUIRED("값을 반드시 입력해야 합니다."),
    INVALID_INPUT_FORMAT("입력 형식이 올바르지 않습니다."),

    AMOUNT_NOT_POSITIVE("금액은 양수여야 합니다."),
    AMOUNT_NOT_MULTIPLE_OF_LOTTO_PRICE("금액이 " + Constants.LOTTO_PRICE + "의 배수가 아닙니다."),

    INVALID_NUMBER_COUNT("로또 번호는 " + Constants.LOTTO_NUMBER_COUNT + "개여야 합니다."),
    INVALID_NUMBER_RANGE("번호가 " + Constants.LOTTO_NUMBER_MIN + "이상 "
            + Constants.LOTTO_NUMBER_MAX + "이하여야 합니다."),
    NUMBER_DUPLICATED("번호가 중복되었습니다."),
    BONUS_NUMBER_DUPLICATED("보너스 번호가 당첨 번호와 중복되었습니다."),

    LOTTO_COUNT_NOT_POSITIVE("로또 개수는 양수여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getFormatMessage() {
        return "[ERROR] " + getMessage();
    }

    public String getMessage() {
        return message;
    }

}
