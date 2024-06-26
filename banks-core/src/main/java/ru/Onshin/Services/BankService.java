package ru.Onshin.Services;

import ru.Onshin.Accounts.Accountable;
import ru.Onshin.Banks.BalanceDepositInterestPair;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.OperationResult.OperationResult;

import java.util.ArrayList;

public class BankService {
    private Bankable _bank;

    public BankService(Bankable bank) {
        _bank = bank;
    }

    public OperationResult createClient(int id, String name, String lastName, String address, String passportNumber) {
        return _bank.createClient(id, name, lastName, address, passportNumber);
    }

    public OperationResult createDebitAccount(int id, Clientable owner) {
        return _bank.createDebitAccount(id, owner);
    }

    public OperationResult createDepositAccount(int id, int term, Clientable owner) {
        return _bank.createDepositAccount(id, term, owner);
    }

    public OperationResult createCreditAccount(int id, double limit, Clientable owner) {
        return _bank.createCreditAccount(id, limit, owner);
    }

    public void setDebitInterest(double debitInterest) {
        _bank.setDebitInterest(debitInterest);
    }

    public void setDepositInterests(ArrayList<BalanceDepositInterestPair> depositInterests) {
        _bank.setDepositInterest(depositInterests);
    }

    public void setCommission(double commission) {
        _bank.setCommission(commission);
    }

    public OperationResult undoTransaction(Accountable account) {
        return _bank.undoTransaction(account);
    }
}
