package lotto.domain.vo.lotto;

import java.util.List;

public record Lottos(List<Lotto> lottos) {
    public int getLottoCount() {
        return lottos.size();
    }
}
