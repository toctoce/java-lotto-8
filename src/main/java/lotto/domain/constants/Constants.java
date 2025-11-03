package lotto.domain.constants;

public class Constants {

    public static final String DELIMITER = ",";

    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    public static final int LOTTO_PRICE = 1000;

    public static final long FIRST_PRIZE = 2_000_000_000;
    public static final long SECOND_PRIZE = 30_000_000;
    public static final long THIRD_PRIZE = 1_500_000;
    public static final long FOURTH_PRIZE = 50_000;
    public static final long FIFTH_PRIZE = 5_000;
    public static final long NO_PRIZE = 0;

    // 객체 생성 방지
    private Constants() {}
}
