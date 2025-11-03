package lotto.vo.lottoresult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lotto.vo.lottoresult.LottoRank;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoRankTest {

    @ParameterizedTest
    @MethodSource(value = "generateData")
    void valueOf(int  matchCount, boolean matchBonus, LottoRank expected) {
        // given, when
        LottoRank actual = LottoRank.valueOf(matchCount, matchBonus);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("6", false, LottoRank.FIRST),
                Arguments.of("5", true, LottoRank.SECOND),
                Arguments.of("5", false, LottoRank.THIRD),
                Arguments.of("4", true, LottoRank.FOURTH),
                Arguments.of("4", false, LottoRank.FOURTH),
                Arguments.of("3", true, LottoRank.FIFTH),
                Arguments.of("3", false, LottoRank.FIFTH),
                Arguments.of("2", true, LottoRank.MISS),
                Arguments.of("2", false, LottoRank.MISS),
                Arguments.of("1", true, LottoRank.MISS),
                Arguments.of("1", false, LottoRank.MISS),
                Arguments.of("0", true, LottoRank.MISS),
                Arguments.of("0", false, LottoRank.MISS));
    }
}