package lotto.vo.lottoresult;

import java.util.Arrays;
import lotto.constants.Constants;

public enum LottoRank {

    FIRST(6, false, Constants.FIRST_PRIZE),
    SECOND(5, true, Constants.SECOND_PRIZE),
    THIRD(5, false, Constants.THIRD_PRIZE),
    FOURTH(4, false, Constants.FOURTH_PRIZE),
    FIFTH(3, false, Constants.FIFTH_PRIZE),
    MISS(0, false, Constants.NO_PRIZE);

    private final int matchCount;
    private final boolean matchBonus;
    private final long prize;

    LottoRank(int matchCount, boolean matchBonus, long prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public static LottoRank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount == 5) {
            if (matchBonus) {
                return SECOND;
            }
            return THIRD;
        }

        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }

    public long getPrize() {
        return prize;
    }
}