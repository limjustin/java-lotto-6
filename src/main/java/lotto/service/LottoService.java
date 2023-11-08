package lotto.service;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoService {

    private static final int LOTTO_LENGTH = 6;
    private static final int LOTTO_START = 1;
    private static final int LOTTO_END = 45;

    public int buy() {
        while(true) {
          int purchaseAmount = Integer.parseInt(Console.readLine());

          if (validatePurchaseAmount(purchaseAmount))
            return purchaseAmount / 1000;
        }
    }

    public List<Lotto> show(int ticket) {
        List<Lotto> lottos = new ArrayList<>();

        for(int i = 0; i < ticket; i++) {
          Lotto lotto = createLotto();
          lottos.add(lotto);
        }

        return lottos;
    }

    public Boolean validatePurchaseAmount(int purchaseAmount) {
        try {
          checkDivideIntoThousand(purchaseAmount);
          return true;
        } catch (IllegalArgumentException e) {
          System.out.println("[ERROR] 1000원으로 나누어 떨어지지 않습니다.");
        }
        return false;
    }

    public void checkDivideIntoThousand(int num) {
        if (num % 1000 != 0) {
          throw new IllegalArgumentException();
        }
    }

    public Lotto createLotto() {
        Lotto lotto;
        List<Integer> numbers = new ArrayList<>();

        for(int i = 0; i < LOTTO_LENGTH; i++) {
          numbers = Randoms.pickUniqueNumbersInRange(LOTTO_START, LOTTO_END, LOTTO_LENGTH);
          Collections.sort(numbers);
        }

        return new Lotto(numbers);
    }
}
