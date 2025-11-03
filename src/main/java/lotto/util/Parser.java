package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import lotto.common.constants.Constants;
import lotto.common.exception.LottoException;
import lotto.common.message.ErrorMessage;
import lotto.vo.Budget;
import lotto.vo.DrawnLottoNumber;
import lotto.vo.lotto.Lotto;

public class Parser {

    private static final Pattern INTEGER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern NUMBERS_PATTERN = Pattern.compile("[\\d+,]*\\d+");

    public static Budget InputToBudget(String input) {
        validateNotBlank(input);
        validateFormat(input, INTEGER_PATTERN);
        int amount = Integer.parseInt(input);
        return new Budget(amount);
    }

    public static DrawnLottoNumber InputToDrawnLottoNumber(String numbersInput, String bonusNumberInput) {
        Lotto lotto = inputToLotto(numbersInput);
        Integer bonusNumber = inputToNumber(bonusNumberInput);
        return new DrawnLottoNumber(lotto, bonusNumber);
    }

    private static Lotto inputToLotto(String input) {
        validateNotBlank(input);
        validateFormat(input, NUMBERS_PATTERN);
        String[] splitResult = split(input, Constants.DELIMITER);
        validateNumberCount(splitResult);
        List<Integer> numbers = splitResultToNumberList(splitResult);
        return new Lotto(numbers);
    }

    private static Integer inputToNumber(String input) {
        validateNotBlank(input);
        validateFormat(input, INTEGER_PATTERN);
        return parseInt(input);
    }

    private static List<Integer> splitResultToNumberList(String[] splitResult) {
        return Arrays.stream(splitResult)
                .map(Parser::parseInt)
                .collect(Collectors.toList());
    }

    private static String[] split(String input, String delimiter) {
        try {
            return input.split(delimiter);
        } catch (PatternSyntaxException e) {
            throw new LottoException(ErrorMessage.INVALID_INPUT_FORMAT);
        }
    }

    private static Integer parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorMessage.NOT_AN_INTEGER);
        }
    }

    private static void validateNumberCount(String[] splitResult) {
        if (splitResult.length != Constants.LOTTO_NUMBER_COUNT) {
            throw new LottoException(ErrorMessage.INVALID_NUMBER_COUNT);
        }
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw new LottoException(ErrorMessage.INPUT_REQUIRED);
        }
    }

    private static void validateFormat(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new LottoException(ErrorMessage.INVALID_INPUT_FORMAT);
        }
    }
}
