package ru.Onshin.Transactions;

import ru.Onshin.Accounts.Accountable;
import ru.Onshin.OperationResult.OperationResult;

public class Refill implements Transactionable {
    private final double _moneyValue;

    public Refill(double moneyValue) {
        _moneyValue = moneyValue;
    }

    public double getMoneyValue() { return _moneyValue; }

    public OperationResult execute(Accountable account) {
        return account.refill(_moneyValue);
    }

    public OperationResult undo(Accountable account) {
        return account.withdraw(_moneyValue);
    }
}
