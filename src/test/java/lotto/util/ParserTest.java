package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.constants.Constants;
import lotto.domain.message.ErrorMessage;
import lotto.domain.vo.Budget;
import lotto.domain.vo.DrawnLottoNumber;
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
                    .isInstanceOf(IllegalArgumentException.class);
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
    void 당첨번호_보너스번호_입력_정상동작(String inputNumbers, String bonusNumber, DrawnLottoNumber expected) {
        // when
        DrawnLottoNumber drawnLottoNumber = Parser.InputToDrawnLottoNumber(inputNumbers, bonusNumber);
        // then
        assertThat(drawnLottoNumber).isEqualTo(expected);
    }

    static Stream<Arguments> generateNumbersData() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6", "7",
                        new DrawnLottoNumber(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7)),
                Arguments.of("10,20,30,40,41,45", "1",
                        new DrawnLottoNumber(new Lotto(List.of(10, 20, 30, 40, 41, 45)), 1)),
                Arguments.of("31,32,33,34,35,36", "37",
                        new DrawnLottoNumber(new Lotto(List.of(31, 32, 33, 34, 35, 36)), 37))
        );
    }

    @Nested
    @DisplayName("당첨번호입력 예외케이스")
    class NumbersInputTest {
        private final String BONUS_NUMBER = "45";
        @ParameterizedTest
        @ValueSource(strings = {"a,b,c,d,e,f", " , , , , , ", "1,2,3,4,5,x", "1, 2, 3, 4, 5, 6"})
        void 숫자_쉼표_이외의_다른_문자가_입력된다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.InputToDrawnLottoNumber(input, BONUS_NUMBER))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "1,2", "1,2,3", "1,2,3,4", "1,2,3,4,5"})
        void 로또_숫자는_6개여야_한다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.InputToDrawnLottoNumber(input, BONUS_NUMBER))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_COUNT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"0,1,2,3,4,5", "1,2,3,4,5,46"})
        void 로또_숫자는_1이상_45이하여야_한다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.InputToDrawnLottoNumber(input, BONUS_NUMBER))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,1,2,3,4,5", "40,40,41,42,43,44", "40,41,42,43,44,44", "1,2,3,4,5,1"})
        void 숫자는_중복되면_안된다(String input) {
            assertThatThrownBy(() -> Parser.InputToDrawnLottoNumber(input, BONUS_NUMBER))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.NUMBER_DUPLICATED.getMessage());
        }
    }

    @Nested
    @DisplayName("보너스번호입력 예외케이스")
    class BonusNumberInputTest {
        private final String NUMBERS = "1,2,3,4,5,6";
        @ParameterizedTest
        @ValueSource(strings = {"a", "-1", "45 ", " 45", " "})
        void 숫자_쉼표_이외의_다른_문자가_입력된다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.InputToDrawnLottoNumber(NUMBERS, input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "46"})
        void 로또_숫자는_1이상_45이하여야_한다(String input) {
            // when, then
            assertThatThrownBy(() -> Parser.InputToDrawnLottoNumber(NUMBERS, input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        }
        @ParameterizedTest
        @ValueSource(strings = {"1", "2", "3", "4", "5", "6"})
        void 보너스번호는_로또번호와_중복되면_안된다(String input) {
            assertThatThrownBy(() -> Parser.InputToDrawnLottoNumber(NUMBERS, input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.BONUS_NUMBER_DUPLICATED.getMessage());
        }
    }


}