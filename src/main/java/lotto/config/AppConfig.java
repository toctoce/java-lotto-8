package lotto.config;

import lotto.controller.LottoController;
import lotto.service.LottoGenerator;
import lotto.service.LottoResultGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    public OutputView outputView() {
        return new OutputView();
    }

    public InputView inputView() {
        return new InputView();
    }

    public LottoGenerator lottoGenerator() {
        return new LottoGenerator();
    }

    public LottoResultGenerator lottoResultGenerator() {
        return new LottoResultGenerator();
    }

    public LottoController lottoController() {
        return new LottoController(
                inputView(),
                outputView(),
                lottoGenerator(),
                lottoResultGenerator()
        );
    }
}