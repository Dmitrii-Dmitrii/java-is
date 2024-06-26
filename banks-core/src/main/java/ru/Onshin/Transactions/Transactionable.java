package ru.Onshin.Transactions;

import ru.Onshin.Accounts.Accountable;
import ru.Onshin.OperationResult.OperationResult;

public interface Transactionable {
    double getMoneyValue();
    OperationResult execute(Accountable account);
    OperationResult undo(Accountable account);
}
