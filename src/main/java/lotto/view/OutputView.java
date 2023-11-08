package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {

    public void printInputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printShowLotto(int ticket) {
        System.out.println("\n" + ticket + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos)
            System.out.println(lotto.getNumbers());
    }

    public void printInputWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public void printInputBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }
}
