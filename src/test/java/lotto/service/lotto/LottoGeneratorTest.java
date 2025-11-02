package lotto.service.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.vo.Budget;
import lotto.domain.vo.LottoCount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoGeneratorTest {

    @Test
    void 로또_생성() {
        // given, when
        Lotto lotto = LottoGenerator.createLotto();

        // then
        assertThat(lotto).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"1000,1", "5000,5", "10000,10", "200000000,200000"})
    void 로또_모두_생성(int amount, int count) {
        // given, when
        Lottos lottos = LottoGenerator.createLottos(LottoCount.of(new Budget(amount)));

        // then
        assertThat(lottos).isNotNull();
        assertThat(lottos.getLottoCount()).isEqualTo(count);
    }

}