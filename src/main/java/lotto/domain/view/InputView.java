package lotto.domain.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.message.ViewMessage;

public class InputView {
    public String readBudget() {
        System.out.println(ViewMessage.BUDGET_INPUT_MESSAGE.getMessage());
        return Console.readLine();
    }
    public String readDrawnLottoNumbers() {
        System.out.println(ViewMessage.DRAWN_LOTTO_NUMBERS_INPUT_MESSAGE.getMessage());
        return Console.readLine();
    }
    public String readDrawnLottoBonusNumber() {
        System.out.println(ViewMessage.DRAWN_LOTTO_BONUS_NUMBER_INPUT_MESSAGE.getMessage());
        return Console.readLine();
    }
}

