package lotto.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.constants.Constants;
import lotto.domain.message.ErrorMessage;
import lotto.domain.vo.Budget;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParserTest {

    @ParameterizedTest
    @CsvSource(value = {"1000,1000", "2000,2000", "200000000,200000000"})
    void 금액입력_정상동작(String input, int expected) {
        // when
        Budget budget = Parser.InputToBudget(input);
        // then
        Assertions.assertThat(budget).isEqualTo(new Budget(expected));
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
                    .hasMessage(ErrorMessage.NOT_A_NUMBER.getMessage());
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

}