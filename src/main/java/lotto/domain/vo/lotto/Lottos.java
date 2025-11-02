package lotto.domain.vo.lotto;

import java.util.List;

public record Lottos(List<Lotto> lottos) {
    public List<Lotto> getLottos() {
        return lottos;
    }
    public int getLottoCount() {
        return lottos.size();
    }
}
