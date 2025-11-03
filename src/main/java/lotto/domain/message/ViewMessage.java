package lotto.domain.message;

import java.text.DecimalFormat;
import lotto.domain.constants.Constants;

public enum ViewMessage {
    BUDGET_INPUT_MESSAGE("구입금액을 입력해 주세요."),
    DRAWN_LOTTO_NUMBERS_INPUT_MESSAGE("당첨 번호를 입력해 주세요."),
    DRAWN_LOTTO_BONUS_NUMBER_INPUT_MESSAGE("보너스 번호를 입력해 주세요."),

    LOTTO_COUNT_OUTPUT_MESSAGE("%s개를 구매했습니다.", true),
    DRAW_LOTTO_RESULT_OUTPUT_MESSAGE("당첨 통계"),
    RATE_OF_RETURN_OUTPUT_MESSAGE("총 수익률은 %s%%입니다.", true),

    FIRST_MESSAGE("6개 일치 (" + longToWon(Constants.FIRST_PRIZE) + ") - %s개", true),
    SECOND_MESSAGE("5개 일치, 보너스 볼 일치 (" + longToWon(Constants.SECOND_PRIZE) + ") - %s개", true),
    THIRD_MESSAGE("5개 일치 (" + longToWon(Constants.THIRD_PRIZE) + ") - %s개", true),
    FOURTH_MESSAGE("4개 일치 (" + longToWon(Constants.FOURTH_PRIZE) + ") - %s개", true),
    FIFTH_MESSAGE("3개 일치 (" + longToWon(Constants.FIFTH_PRIZE) + ") - %s개", true);


    private final String message;
    private final boolean isFormatted;

    ViewMessage(String message) {
        this.message = message;
        this.isFormatted = false;
    }

    ViewMessage(String message, boolean isFormatted) {
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

    private static String longToWon(long amount) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        String formattedAmount = formatter.format(amount);
        return formattedAmount + "원";
    }
}
