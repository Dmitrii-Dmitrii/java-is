package ru.Onshin.Transactions;

import ru.Onshin.Accounts.Accountable;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.OperationResult.OperationResult;

public class Transfer implements Transactionable {
    private final double _moneyValue;
    private final Accountable _recipientAccount;
    private final Bankable _senderBank;
    private final Bankable _recipientBank;

    public Transfer(double moneyValue, Accountable recipientAccount, Bankable senderBank, Bankable recipientBank) {
        _moneyValue = moneyValue;
        _recipientAccount = recipientAccount;
        _senderBank = senderBank;
        _recipientBank = recipientBank;
    }

    public double getMoneyValue() { return _moneyValue; }

    public Accountable getRecipientAccount() { return _recipientAccount; }

    public OperationResult execute(Accountable account) {
        if (account.getBank() != _senderBank || _recipientAccount.getBank() != _recipientBank || account.withdraw(_moneyValue) == OperationResult.FAIL)
            return OperationResult.FAIL;

        account.withdraw(_moneyValue);
        _recipientAccount.refill(_moneyValue);
        return OperationResult.SUCCESS;
    }

    public OperationResult undo(Accountable account) {
        _recipientAccount.withdraw(_moneyValue);
        account.refill(_moneyValue);
        return OperationResult.SUCCESS;
    }
}
