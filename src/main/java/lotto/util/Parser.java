package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import lotto.domain.constants.Constants;
import lotto.domain.message.ErrorMessage;
import lotto.domain.vo.Budget;

public class Parser {

    private static final Pattern INTEGER_PATTERN = Pattern.compile("-?\\d+");
    private static final Pattern NUMBERS_PATTERN = Pattern.compile("[\\d+,]*\\d+");

    public static Budget InputToBudget(String input) {
        validateNotBlank(input);
        validateInteger(input);
        int amount = Integer.parseInt(input);
        return new Budget(amount);
    }

    public static List<Integer> inputToNumbers(String input) {
        validateNotBlank(input);
        validateFormat(input, NUMBERS_PATTERN);
        String[] splitResult = split(input, Constants.DELIMITER);
        validateNumberCount(splitResult);
        return splitResultToNumberList(splitResult);
    }

    private static List<Integer> splitResultToNumberList(String[] splitResult) {
        return Arrays.stream(splitResult)
                .map(Integer::parseInt)
                .toList();
    }

    private static String[] split(String input, String delimiter) {
        try {
            String[] splitResult = input.split(delimiter);
            return splitResult;
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
    }

    private static void validateNumberCount(String[] splitResult) {
        if (splitResult.length != Constants.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_COUNT.getMessage());
        }
    }

    private static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_A_NUMBER.getMessage());
        }
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_REQUIRED.getMessage());
        }
    }

    private static void validateFormat(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
    }

}
