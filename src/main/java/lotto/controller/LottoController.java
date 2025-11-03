package lotto.controller;

import lotto.domain.vo.Budget;
import lotto.domain.vo.DrawnLottoNumber;
import lotto.domain.vo.LottoCount;
import lotto.domain.vo.lotto.Lottos;
import lotto.domain.vo.lottoresult.LottoResults;
import lotto.service.lotto.LottoGenerator;
import lotto.service.lotto.LottoResultGenerator;
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

        DrawnLottoNumber drawnLottoNumber = createDrawnLottoNumberFromUserInput();
        LottoResults lottoResults = createLottoResults(budget, lottos, drawnLottoNumber);
        outputView.writeLottoResults(lottoResults);
    }

    private LottoResults createLottoResults(Budget budget, Lottos lottos, DrawnLottoNumber drawnLottoNumber) {
        return lottoResultGenerator.createLottoResults(budget, lottos, drawnLottoNumber);
    }

    private Lottos createLottos(Budget budget) {
        return lottoGenerator.createLottos(LottoCount.of(budget));
    }

    private DrawnLottoNumber createDrawnLottoNumberFromUserInput() {
        try {
            String drawnLottoNumbersInput = inputView.readDrawnLottoNumbers();
            String drawnLottoBonusNumber = inputView.readDrawnLottoBonusNumber();
            return Parser.InputToDrawnLottoNumber(drawnLottoNumbersInput, drawnLottoBonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.writeError(e.getMessage());
            return createDrawnLottoNumberFromUserInput();
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
