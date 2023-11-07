package lotto.controller;

import lotto.service.LottoService;
import lotto.view.OutputView;

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
}
