package lotto.controller;

import lotto.vo.Budget;
import lotto.vo.DrawnLotto;
import lotto.vo.LottoCount;
import lotto.vo.lotto.Lottos;
import lotto.vo.lottoresult.LottoResults;
import lotto.service.LottoGenerator;
import lotto.service.LottoResultGenerator;
import lotto.util.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;
    private final LottoResultGenerator lottoResultGenerator;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            LottoGenerator lottoGenerator,
            LottoResultGenerator lottoResultGenerator
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
        this.lottoResultGenerator = lottoResultGenerator;
    }

    public void run() {
        Budget budget = createBudgetFromUserInput();
        Lottos lottos = createLottos(budget);
        outputView.writePurchaseHistory(lottos);

        DrawnLotto drawnLotto = createDrawnLottoFromUserInput();
        LottoResults lottoResults = createLottoResults(budget, lottos, drawnLotto);
        outputView.writeLottoResults(lottoResults);
    }

    private LottoResults createLottoResults(Budget budget, Lottos lottos, DrawnLotto drawnLotto) {
        return lottoResultGenerator.createLottoResults(budget, lottos, drawnLotto);
    }

    private Lottos createLottos(Budget budget) {
        return lottoGenerator.createLottos(LottoCount.of(budget));
    }

    private DrawnLotto createDrawnLottoFromUserInput() {
        try {
            String numbersInput = inputView.readDrawnLottoNumbers();
            String bonusNumberInput = inputView.readDrawnLottoBonusNumber();
            return Parser.InputToDrawnLotto(numbersInput, bonusNumberInput);
        } catch (IllegalArgumentException e) {
            outputView.writeError(e.getMessage());
            return createDrawnLottoFromUserInput();
        }
    }

    private Budget createBudgetFromUserInput() {
        try {
            String budgetInput = inputView.readBudget();
            return Parser.InputToBudget(budgetInput);
        } catch (IllegalArgumentException e) {
            outputView.writeError(e.getMessage());
            return createBudgetFromUserInput();
        }
    }

}
