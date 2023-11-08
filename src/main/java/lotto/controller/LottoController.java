package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.WinningNumber;
import lotto.service.LottoService;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private OutputView outputView;
    private LottoService lottoService;

    public LottoController() {
        outputView = new OutputView();
        lottoService = new LottoService();
    }

    public int buy() {
        outputView.printInputPurchaseAmount();
        return lottoService.buy();
    }

    public void show(int ticket) {
        outputView.printShowLotto(ticket);
        List<Lotto> lottos = lottoService.show(ticket);
        outputView.printLottos(lottos);
    }

    public void draw() {
        outputView.printInputWinningNumbers();
        List<Integer> winningNumbers = lottoService.drawWinningNumbers();
        outputView.printInputBonusNumber();
        WinningNumber winningNumber = lottoService.drawBonusNumber(winningNumbers);
    }
}
