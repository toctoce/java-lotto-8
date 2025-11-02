package lotto.domain.lotto;

import java.util.List;

public record Lottos(List<Lotto> lottos) {
    public List<Lotto> getLottos() {
        return lottos;
    }
    public int getLottosCount() {
        return lottos.size();
    }
}
