package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.stream.Stream;
import lotto.domain.constants.Constants;
import lotto.domain.message.ErrorMessage;
import lotto.domain.vo.Budget;
import lotto.domain.vo.lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParserTest {

    @ParameterizedTest
    @CsvSource(value = {"1000,1000", "2000,2000", "200000000,200000000"})
    void 금액입력_정상동작(String input, int expected) {
        // when
        Budget budget = Parser.InputToBudget(input);
        // then
        assertThat(budget).isEqualTo(new Budget(expected));
    }

    @Nested
    @DisplayName("금액입력 예외케이스")
    class InputToBudgetTest {
        @ParameterizedTest
        @NullSource()
        void 입력값이_존재해야_한다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.InputToBudget(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INPUT_REQUIRED.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"a", "1000원", "1,2", "1000 ", " "})
        void 숫자만_입력되어야_한다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.InputToBudget(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "-1000"})
        void 금액은_양수여야_한다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.InputToBudget(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.AMOUNT_NOT_POSITIVE.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"100", "1001", "1100"})
        void 금액은_로또금액_1000의_배수여야_한다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.InputToBudget(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.AMOUNT_NOT_MULTIPLE_OF_LOTTO_PRICE.getMessage(
                            Integer.toString(Constants.LOTTO_PRICE)
                    ));
        }
    }

    @ParameterizedTest
    @MethodSource("generateNumbersData")
    void 번호입력_정상동작(String input, Lotto expected) {
        // when
        Lotto lotto = Parser.inputToLotto(input);
        // then
        assertThat(lotto).isEqualTo(expected);
    }

    static Stream<Arguments> generateNumbersData() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6", new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6))),
                Arguments.of("10,20,30,40,41,45", new Lotto(Arrays.asList(10, 20, 30, 40, 41, 45))),
                Arguments.of("31,32,33,34,35,36", new Lotto(Arrays.asList(31, 32, 33, 34, 35, 36)))
        );
    }

    @Nested
    @DisplayName("번호입력 예외케이스")
    class InputToNumbersTest {

        @ParameterizedTest
        @ValueSource(strings = {"a,b,c,d,e,f", " , , , , , ", "1,2,3,4,5,x", "1, 2, 3, 4, 5, 6"})
        void 숫자_쉼표_이외의_다른_문자가_입력된다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.inputToLotto(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "1,2", "1,2,3", "1,2,3,4", "1,2,3,4,5"})
        void 로또_숫자는_6개여야_한다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.inputToLotto(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_COUNT.getMessage());
        }
//        TODO: 숫자 범위 검증을 어디서 할지 고민
//        @ParameterizedTest
//        @ValueSource(strings = {"0,1,2,3,4,5", "1,2,3,4,5,46", "-1,0,1,2,3,4"})
//        void 로또_숫자는_1이상_45이하여야_한다(String input) {
//            // when, then
//            assertThatThrownBy(() -> Parser.inputToNumbers(input))
//                    .isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
//        }
    }

}