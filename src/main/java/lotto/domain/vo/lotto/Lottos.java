package lotto.domain.vo.lotto;

import java.util.List;
import java.util.stream.Collectors;

public record Lottos(List<Lotto> lottos) {

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
