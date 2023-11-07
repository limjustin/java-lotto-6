package lotto.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}