package lotto.service.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.vo.lotto.Lotto;
import lotto.vo.lotto.Lottos;
import lotto.vo.Budget;
import lotto.vo.LottoCount;
import lotto.service.LottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoGeneratorTest {

    private LottoGenerator lottoGenerator;

    @BeforeEach
    void setUp() {
        lottoGenerator = new LottoGenerator();
    }

    @Test
    void 로또_생성() {
        // given, when
        Lotto lotto = lottoGenerator.createLotto();

        // then
        assertThat(lotto).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"1000,1", "5000,5", "10000,10", "200000000,200000"})
    void 로또_모두_생성(int amount, int count) {
        // given, when
        Lottos lottos = lottoGenerator.createLottos(LottoCount.of(new Budget(amount)));

        // then
        assertThat(lottos).isNotNull();
        assertThat(lottos.getLottoCount()).isEqualTo(count);
    }
}