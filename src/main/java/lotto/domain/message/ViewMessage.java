package lotto.domain.message;

public enum ViewMessage {
    BUDGET_INPUT_MESSAGE("구입금액을 입력해 주세요."),
    WINNING_LOTTO_NUMBERS_INPUT_MESSAGE("당첨 번호를 입력해 주세요."),
    WINNING_LOTTO_BONUS_NUMBER_INPUT_MESSAGE("보너스 번호를 입력해 주세요."),

    LOTTO_TICKET_COUNT_OUTPUT_MESSAGE("%d개를 구매했습니다."),
    LOTTOS_RESULT_OUTPUT_MESSAGE("당첨 통계"),

    DIVIDER("---");

    private final String message;

    ViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
