package lotto.vo.lotto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.vo.LottoCount;

public record Lottos(List<Lotto> lottos, LottoCount lottoCount) {

    public static final String DELIMITER = "\n";

    public int getLottoCount() {
        return lottos.size();
    }

    @Override
    public String toString() {
        return lottos.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining(DELIMITER));
    }
}
