package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lotto.vo.Budget;
import lotto.vo.DrawnLotto;
import lotto.vo.LottoCount;
import lotto.vo.lotto.Lotto;
import lotto.vo.lotto.Lottos;
import lotto.vo.lottoresult.LottoResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultGeneratorTest {

    private LottoResultGenerator lottoResultGenerator;

    @BeforeEach
    void setUp() {
        lottoResultGenerator = new LottoResultGenerator();
    }

    @ParameterizedTest
    @MethodSource(value = "generateData")
    void createLottoResults(DrawnLotto drawnLotto, long expectedPrize) {
        Lottos lottos = dummyLottos();

        LottoResults lottoResults = lottoResultGenerator.createLottoResults(new Budget(1000), lottos, drawnLotto);
        long prize = lottoResults.getPrize();

        assertThat(prize).isEqualTo(expectedPrize);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(createDrawnLotto(List.of(1, 2, 3, 4, 5, 6), 7), 2_000_005_000L), // 1. 1등, 2등
                Arguments.of(createDrawnLotto(List.of(21, 22, 23, 24, 25, 26), 7), 2_000_000_000L), // 2. 1등
                Arguments.of(createDrawnLotto(List.of(21, 22, 23, 24, 25, 45), 7), 1_500_000L), // 3. 3등
                Arguments.of(createDrawnLotto(List.of(21, 22, 23, 24, 25, 45), 26), 30_000_000L), // 4. 2등
                Arguments.of(createDrawnLotto(List.of(31, 32, 33, 34, 35, 36), 7), 4_000_000_000L), // 5. 1등, 1등
                Arguments.of(createDrawnLotto(List.of(32, 33, 34, 35, 36, 37), 7), 3_000_000L), // 6. 3등, 3등
                Arguments.of(createDrawnLotto(List.of(13, 14, 15, 16, 17, 18), 7), 2_001_500_000L), // 7. 1등, 3등
                Arguments.of(createDrawnLotto(List.of(1, 14, 15, 16, 17, 18), 7), 3_000_000L), // 8. 3등, 3등
                Arguments.of(createDrawnLotto(List.of(23, 24, 25, 26, 44, 45), 21), 50_000L), // 9. 4등
                Arguments.of(createDrawnLotto(List.of(24, 25, 26, 43, 44, 45), 21), 5_000L), // 10. 5등
                Arguments.of(createDrawnLotto(List.of(4, 5, 6, 7, 44, 45), 43), 55_000L), // 11. 4, 5등
                Arguments.of(createDrawnLotto(List.of(4, 5, 6, 43, 44, 45), 42), 10_000L) // 12. 5, 5등
        );
    }

    private static DrawnLotto createDrawnLotto(List<Integer> numbers, Integer bonusNumber) {
        Lotto lotto = createLotto(new ArrayList<>(numbers));
        return new DrawnLotto(lotto, bonusNumber);
    }

    private Lottos dummyLottos() {
        return createLottos(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12),
                List.of(4, 5, 6, 7, 8, 9),
                List.of(21, 22, 23, 24, 25, 26),
                List.of(31, 32, 33, 34, 35, 36),
                List.of(31, 32, 33, 34, 35, 36),
                List.of(13, 14, 15, 16, 17, 18),
                List.of(14, 15, 16, 17, 18, 19)
        ));
    }

    private static Lotto createLotto(List<Integer> lottoNumbers) {
        return new Lotto(new ArrayList<>(lottoNumbers));
    }

    private Lottos createLottos(List<List<Integer>> lottoNumbers) {

        List<Lotto> lottos = lottoNumbers.stream()
                .map(LottoResultGeneratorTest::createLotto)
                .toList();

        return new Lottos(lottos, new LottoCount(lottoNumbers.size()));
    }
}