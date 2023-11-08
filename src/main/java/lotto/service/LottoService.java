package lotto.service;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.WinningNumber;

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

        for (int i = 0; i < ticket; i++) {
          Lotto lotto = createLotto();
          lottos.add(lotto);
        }

        return lottos;
    }

    public List<Integer> drawWinningNumbers() {
        List<Integer> winningNumbers = new ArrayList<>();
        String inputNumbers;

        while (true) {
            inputNumbers = Console.readLine();

            if(validateWinningNumbers(inputNumbers))
                break;
        }

        String[] inputNumbersSplit = inputNumbers.split(",");

        for (String inputNumber : inputNumbersSplit)
            winningNumbers.add(Integer.parseInt(inputNumber));

        return winningNumbers;
    }

    public WinningNumber drawBonusNumber(List<Integer> winningNumbers) {
        String inputNumber;

        while (true) {
            inputNumber = Console.readLine();

            if (validateBonusNumbers(inputNumber, winningNumbers))
                break;
        }

        return new WinningNumber(winningNumbers, Integer.parseInt(inputNumber));
    }

    public Lotto createLotto() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < LOTTO_LENGTH; i++) {
            numbers = Randoms.pickUniqueNumbersInRange(LOTTO_START, LOTTO_END, LOTTO_LENGTH);
            Collections.sort(numbers);
        }

        return new Lotto(numbers);
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

    public Boolean validateWinningNumbers(String inputNumbers) {
        try {
            checkContainCharacter(inputNumbers);
            checkContainBlank(inputNumbers.split(","));
            checkLength(inputNumbers.split(","));
            checkRangeOfNumber(inputNumbers.split(","));
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 올바르지 않은 당첨 번호 입력입니다.");
        }
        return false;
    }

    public Boolean validateBonusNumbers(String inputNumber, List<Integer> winningNumbers) {
        try {
            checkContainCharacter(inputNumber);
            checkRangeOfOneNumber(inputNumber);
            checkBonusNumberInWinningNumbers(inputNumber, winningNumbers);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 올바르지 않은 보너스 번호 입력입니다.");
        }
        return false;
    }

    public void checkDivideIntoThousand(int num) {
        if (num % 1000 != 0)
          throw new IllegalArgumentException();
    }

    public void checkContainCharacter(String str) {
        if (str.matches(".*[a-zA-Zㄱ-ㅎ가-힣].*"))
          throw new IllegalArgumentException();
    }

    public void checkContainBlank(String[] strs) {
        for (String str : strs)
            if(str.isBlank())
                throw new IllegalArgumentException();
    }

    public void checkLength(String[] strs) {
        if (strs.length != LOTTO_LENGTH)
            throw new IllegalArgumentException();
    }

    public void checkRangeOfNumber(String[] strs) {
        for (String str : strs)
            if(Integer.parseInt(str) < LOTTO_START || Integer.parseInt(str) > LOTTO_END)
                throw new IllegalArgumentException();
    }

    public void checkRangeOfOneNumber(String str) {
        if (Integer.parseInt(str) < LOTTO_START || Integer.parseInt(str) > LOTTO_END)
            throw new IllegalArgumentException();
    }

    public void checkBonusNumberInWinningNumbers(String str, List<Integer> winningNumbers) {
        if(winningNumbers.contains(Integer.parseInt(str)))
            throw new IllegalArgumentException();
    }
}
