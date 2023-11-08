package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    void 당첨번호_입력이_문자를_포함하는_경우() {
        // given
        String inputNumbers = "1,2,c,4,5,6";
        // when & then
        assertThatThrownBy(() -> lottoService.checkContainCharacter(inputNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_입력이_공백을_포함하는_경우() {
        // given
        String inputNumbers = "1,2,,4,5,6";
        // then
        assertThatThrownBy(() -> lottoService.checkContainBlank(inputNumbers.split(",")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_입력의_개수가_6이_아닌_경우() {
        // given
        String inputNumbers = "1,2,3,4,5";
        // when & then
        assertThatThrownBy(() -> lottoService.checkLength(inputNumbers.split(",")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_입력의_번호가_로또_숫자의_범위를_벗어나는_경우() {
        // given
        String inputNumbers = "41,42,43,44,45,46";
        // when & them
        assertThatThrownBy(() -> lottoService.checkRangeOfNumber(inputNumbers.split(",")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스번호_입력이_문자를_포함하는_경우() {
        // given
        String inputNumber = "c";
        // when & then
        assertThatThrownBy(() -> lottoService.checkContainCharacter(inputNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스번호_입력의_번호가_로또_숫자의_범위를_벗어나는_경우() {
        // given
        String inputNumber = "46";
        // when & then
        assertThatThrownBy(() -> lottoService.checkRangeOfOneNumber(inputNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스번호_입력의_번호가_당첨번호_입력에_포함되는_경우() {
        // given
        String inputNumber = "6";
        // when
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        // then
        assertThatThrownBy(() -> lottoService.checkBonusNumberInWinningNumbers(inputNumber, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}