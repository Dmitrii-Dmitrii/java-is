package ru.Onshin.Banks;

import ru.Onshin.Accounts.*;
import ru.Onshin.CentralBank.CentralBank;
import ru.Onshin.ClientNotifiers.Notiferable;
import ru.Onshin.Clients.BaseClient;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.OperationResult.OperationResult;
import ru.Onshin.Transactions.Refill;
import ru.Onshin.Transactions.Transactionable;
import ru.Onshin.Transactions.Transfer;
import ru.Onshin.Transactions.Withdraw;

import java.util.ArrayList;
import java.util.Objects;

public class Bank implements Bankable {
    private final int _id;
    private double _debitInterest;
    private ArrayList<BalanceDepositInterestPair> _depositInterests;
    private double _commission;
    private ArrayList<Accountable> _accounts;
    private ArrayList<Clientable> _clients;
    private ArrayList<Clientable> _notifiedClients;
    private final double _verificationLimit;

    public Bank(int id, double debitInterest, ArrayList<BalanceDepositInterestPair> depositInterests, double commission, double verificationLimit) {
        _id = id;
        _debitInterest = debitInterest;
        _depositInterests = depositInterests;
        _commission = commission;
        _accounts = new ArrayList<>();
        _clients = new ArrayList<>();
        _notifiedClients = new ArrayList<>();
        _verificationLimit = verificationLimit;
    }

    public int getId() { return _id; }

    public ArrayList<Accountable> getAccounts() { return _accounts; }

    public double getDebitInterest() { return _debitInterest; }

    public ArrayList<BalanceDepositInterestPair> getDepositInterests() { return _depositInterests; }

    public double getCommission() { return _commission; }

    public ArrayList<Clientable> getClients() { return _clients; }

    public OperationResult subscribeClient(Clientable client) {
        for (Clientable clientInList : _notifiedClients)
            if (clientInList == client)
                return OperationResult.FAIL;

        _notifiedClients.add(client);
        return OperationResult.SUCCESS;
    }

    public void setDebitInterest(double debitInterest) {
        String context = "Debit interest has changed! Old: " + _debitInterest + ", new: " + debitInterest;
        _debitInterest = debitInterest;
        notifyClients(context);
    }

    public void setDepositInterest(ArrayList<BalanceDepositInterestPair> depositInterests) {
        String context = "Deposit interest has changed! Old: " + _depositInterests + ", new: " + depositInterests;
        _depositInterests = depositInterests;
        notifyClients(context);
    }

    public void setCommission(double commission) {
        String context = "Commission has changed! Old: " + _commission + ", new: " + commission;
        _commission = commission;
        notifyClients(context);
    }

    public OperationResult createDebitAccount(int id, Clientable owner) {
        for (Accountable account : _accounts)
            if (id == account.getId())
                return OperationResult.FAIL;

        _accounts.add(new DebitAccount(id, owner, this, _verificationLimit));
        return OperationResult.SUCCESS;
    }

    public OperationResult createDepositAccount(int id, int term, Clientable owner) {
        for (Accountable account : _accounts)
            if (id == account.getId())
                return OperationResult.FAIL;

        _accounts.add(new DepositAccount(id, owner, term,this, _verificationLimit));
        return OperationResult.SUCCESS;
    }

    public OperationResult createCreditAccount(int id, double limit, Clientable owner) {
        for (Accountable account : _accounts)
            if (id == account.getId())
                return OperationResult.FAIL;

        _accounts.add(new CreditAccount(id, owner, limit, this, _verificationLimit));
        return OperationResult.SUCCESS;
    }

    public OperationResult createClient(int id, String name, String lastName, String address, String passportNumber) {
        for (Clientable client : _clients)
            if (id == client.getId())
                return OperationResult.FAIL;

        BaseClient client = new BaseClient.ClientBuilder()
                .addId(id)
                .addName(name)
                .addLastName(lastName)
                .addAddress(address)
                .addPassportNumber(passportNumber)
                .build();
        _clients.add(client);
        return OperationResult.SUCCESS;
    }

    public OperationResult addClientAddress(Clientable client, String address) {
        if (Objects.equals(address, ""))
            return OperationResult.FAIL;

        client.setAddress(address);

        if (!Objects.equals(client.getPassportNumber(), ""))
            for (Accountable account : _accounts)
                if (account.getOwner() == client)
                    account.approveVerification();

        return OperationResult.SUCCESS;
    }

    public OperationResult addClientPassportNumber(Clientable client, String passportNumber) {
        if (Objects.equals(passportNumber, ""))
            return OperationResult.FAIL;

        client.setPassportNumber(passportNumber);

        if (!Objects.equals(client.getAddress(), ""))
            for (Accountable account : _accounts)
                if (account.getOwner() == client)
                    account.approveVerification();

        return OperationResult.SUCCESS;
    }

    public OperationResult refill(Accountable account, double moneyValue) {
        for (Accountable accountInList : _accounts)
            if (accountInList.getId() == account.getId()) {
                Transactionable transaction = new Refill(moneyValue);
                account.addTransaction(transaction);
                return transaction.execute(account);
            }

        return OperationResult.FAIL;
    }

    public OperationResult withdraw(Accountable account, double moneyValue) {
        for (Accountable accountInList : _accounts)
            if (accountInList.getId() == account.getId()) {
                Transactionable transaction = new Withdraw(moneyValue);
                account.addTransaction(transaction);
                return transaction.execute(account);
            }

        return OperationResult.FAIL;
    }

    public OperationResult transfer(Accountable senderAccount, Accountable recipientAccount, Bankable recipientBank, double moneyValue) {
        boolean flag = false;

        if (_id == recipientBank.getId()) {
            for (Accountable account : _accounts)
                if (account.getId() == senderAccount.getId()) {
                    flag = true;
                    break;
                }

            if (!flag)
                return OperationResult.FAIL;

            for (Accountable account : _accounts)
                if (account.getId() == recipientAccount.getId()) {
                    flag = false;
                    break;
                }

            if (flag)
                return OperationResult.FAIL;

            Transactionable transaction = new Transfer(moneyValue, recipientAccount, this, recipientBank);
            senderAccount.addTransaction(transaction);
            recipientAccount.addTransaction(transaction);
            return transaction.execute(senderAccount);
        }

        return CentralBank.getCentralBank().transfer(senderAccount, this, recipientAccount, recipientBank, moneyValue);
    }

    public OperationResult undoTransaction(Accountable account) {
        for (Accountable accountInList : _accounts)
            if (accountInList.getId() == account.getId()) {
                ArrayList<SavedTransaction> transactions = account.getTransactions();
                SavedTransaction transaction = transactions.get(transactions.size() - 1);

                if (transaction.transaction() instanceof Transfer transfer)
                    return CentralBank.getCentralBank().undoTransfer(account, transfer);

                account.deleteLastTransaction();
                return transaction.transaction().undo(account);
            }

        return OperationResult.FAIL;
    }

    public void calculateMonthlyInterest() {
        for (Accountable account : _accounts) {
            account.calculateMonthlyInterest();
        }
    }

    public void calculateDailyInterest() {
        for (Accountable account : _accounts) {
            account.calculateDailyInterest();
        }
    }

    public void notifyClients(String context) {
        for (Clientable client : _notifiedClients)
            for (Notiferable notifier : client.getClientNotifiers())
                notifier.notifyClient(context);
    }
}
