package ru.Onshin.Accounts;

import ru.Onshin.Banks.Bankable;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.OperationResult.OperationResult;
import ru.Onshin.Transactions.Transactionable;

import java.util.ArrayList;
import java.util.Objects;

public class DebitAccount implements Accountable{
    private double _balance = 0;
    private final int _id;
    private final Clientable _owner;
    private final Bankable _bank;
    private boolean _verification;
    private ArrayList<SavedTransaction> _transactions;
    private final double _verificationLimit;
    private double _monthlyInterest = 0;

    public DebitAccount(int id, Clientable owner, Bankable bank, double verificationLimit) {
        _id = id;
        _owner = owner;
        _bank = bank;
        _verification = !Objects.equals(_owner.getAddress(), "") && !Objects.equals(_owner.getPassportNumber(), "");
        _transactions = new ArrayList<>();
        _verificationLimit = verificationLimit;
    }

    public int getId() { return _id; }

    public Clientable getOwner() { return _owner; }

    public Bankable getBank() { return _bank; }

    public void approveVerification() { _verification = true; }

    public double getBalance() { return _balance; }

    public ArrayList<SavedTransaction> getTransactions() { return _transactions; }

    public ArrayList<String> getTransactionHistory() {
        ArrayList<String> transactions = new ArrayList<>();

        for (SavedTransaction transaction : _transactions)
            transactions.add(transaction.transactionType().toString() + ", " +transaction.moneyValue());

        return transactions;
    }

    public OperationResult withdraw(double moneyValue) {
        if (moneyValue > _balance || (!_verification && moneyValue > _verificationLimit))
            return OperationResult.FAIL;
        else {
            _balance -= moneyValue;
            return OperationResult.SUCCESS;
        }
    }

    public OperationResult refill(double moneyValue) {
        _balance += moneyValue;
        return OperationResult.SUCCESS;
    }

    public void addTransaction(Transactionable transaction) { _transactions.add(new SavedTransaction(transaction, transaction.getClass(), transaction.getMoneyValue())); }

    public void deleteLastTransaction() { _transactions.remove(_transactions.size() - 1); }

    public void calculateMonthlyInterest() {
        _balance += _monthlyInterest;
        _monthlyInterest = 0;
    }

    public void calculateDailyInterest() { _monthlyInterest += _balance * _bank.getDebitInterest() / 365; }
}
