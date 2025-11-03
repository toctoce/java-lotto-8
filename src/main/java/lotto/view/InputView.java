package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.message.ViewMessage;

public class InputView {

    private final String NEW_LINE = "\n";

    public String readBudget() {
        System.out.println(ViewMessage.BUDGET_INPUT_MESSAGE.getMessage());
        return Console.readLine();
    }
    public String readDrawnLottoNumbers() {
        System.out.println(NEW_LINE + ViewMessage.DRAWN_LOTTO_NUMBERS_INPUT_MESSAGE.getMessage());
        return Console.readLine();
    }
    public String readDrawnLottoBonusNumber() {
        System.out.println(NEW_LINE + ViewMessage.DRAWN_LOTTO_BONUS_NUMBER_INPUT_MESSAGE.getMessage());
        return Console.readLine();
    }
}
