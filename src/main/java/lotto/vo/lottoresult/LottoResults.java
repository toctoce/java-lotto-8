package lotto.vo.lottoresult;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.vo.Budget;

public record LottoResults(Budget budget, List<LottoResult> results) {

    public Map<LottoRank, Integer> getRankCount() {
        Map<LottoRank, Integer> rankCount = new EnumMap<>(LottoRank.class);
        for (LottoRank rank : LottoRank.values()) {
            rankCount.put(rank, 0);
        }
        for (LottoResult result : results) {
            LottoRank rank = result.rank();
            rankCount.put(rank, rankCount.get(rank) + 1);
        }
        return rankCount;
    }

    public long getPrize() {
        return results.stream()
                .map(LottoResult::rank)
                .mapToLong(LottoRank::getPrize)
                .sum();
    }

    public double getRateOfReturn() {
        return (double) getPrize() / budget.amount() * 100.0;
    }
}
