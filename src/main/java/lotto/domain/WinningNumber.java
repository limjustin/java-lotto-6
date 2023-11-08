package lotto.domain;

import java.util.List;

public class WinningNumber {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumber(List<Integer> winningNumbers, int bonusNumber) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}