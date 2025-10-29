package lotto.message;

public enum ViewMessage {
    BUDGET_INPUT_MESSAGE("구입금액을 입력해 주세요."),
    DRAWN_LOTTO_NUMBERS_INPUT_MESSAGE("당첨 번호를 입력해 주세요."),
    DRAWN_LOTTO_BONUS_NUMBER_INPUT_MESSAGE("보너스 번호를 입력해 주세요."),

    LOTTO_TICKET_COUNT_OUTPUT_MESSAGE("%s개를 구매했습니다.", true),
    DRAW_LOTTO_RESULT_OUTPUT_MESSAGE("당첨 통계"),
    RATE_OF_RETURN_OUTPUT_MESSAGE("총 수익률은 %s%%입니다.", true),

    MATCH_3_MESSAGE("3개 일치 (%s) - %s개", true),
    MATCH_4_MESSAGE("4개 일치 (%s) - %s개", true),
    MATCH_5_MESSAGE("5개 일치 (%s) - %s개", true),
    MATCH_5_AND_BONUS_MESSAGE("5개 일치, 보너스 볼 일치 (%s) - %s개", true),
    MATCH_6_MESSAGE("6개 일치 (%s) - %s개", true),

    DIVIDER("---"),
    NEW_LINE("\n");

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
}
