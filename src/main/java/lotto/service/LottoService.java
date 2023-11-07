package lotto.service;

import camp.nextstep.edu.missionutils.Console;

public class LottoService {

    public int buy() {
        while(true) {
          int purchaseAmount = Integer.parseInt(Console.readLine());

          if (validatePurchaseAmount(purchaseAmount))
            return purchaseAmount / 1000;
        }
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
}
