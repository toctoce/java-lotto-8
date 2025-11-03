package lotto.domain.vo.lottoresult;

import java.util.List;

public record LottoResults(List<LottoResult> results, long prize) {
    public static LottoResults of(List<LottoResult> results) {
        long prize = results.stream()
                .map(LottoResult::rank)
                .mapToLong(LottoRank::getPrize)
                .sum();
        return new LottoResults(results, prize);
    }
}
