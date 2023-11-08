package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoServiceTest {

    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
    }

    @Test
    void 천원으로_나누어_떨어지지_않는_경우() {
        // given
        int purchaseAmount = 7500;
        // when & then
        assertThatThrownBy(() -> lottoService.checkDivideIntoThousand(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 천원으로_나누어_떨어지는_경우() {
        // given
        int purchaseAmount = 8000;
        // when
        Boolean isValidate = lottoService.validatePurchaseAmount(purchaseAmount);
        // then
        assertThat(isValidate).isEqualTo(true);
    }

    @Test
    void 로또_매수만큼_로또_생성하는지_확인() {
        // given
        int ticket = 8;
        // when
        List<Lotto> lottos = lottoService.show(ticket);
        // then
        assertThat(lottos.size()).isEqualTo(ticket);
    }
}