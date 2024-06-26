package ru.Onshin.Services;

import ru.Onshin.Accounts.Accountable;
import ru.Onshin.Accounts.SavedTransaction;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.CentralBank.CentralBank;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.OperationResult.OperationResult;

import java.util.ArrayList;

public class ClientService {
    private Clientable _client;
    private final CentralBank _centralBank;

    public ClientService(Clientable client) {
        _client = client;
        _centralBank = CentralBank.getCentralBank();
    }

    public ArrayList<AccountBankPair> showAccounts() {
        ArrayList<AccountBankPair> accountBankPairs = new ArrayList<>();

        for (Bankable bank : _centralBank.getBanks())
            for (Accountable account : bank.getAccounts())
                if (account.getOwner() == _client)
                    accountBankPairs.add(new AccountBankPair(account.getId(), bank.getId()));

        return accountBankPairs;
    }

    public double showAccountBalance(Accountable account) {
        return account.getBalance();
    }

    public OperationResult refill(Accountable account, Bankable bank, double moneyValue) {
        return bank.refill(account, moneyValue);
    }

    public OperationResult withdraw(Accountable account, Bankable bank, double moneyValue) {
        return bank.withdraw(account, moneyValue);
    }

    public OperationResult transfer(Accountable senderAccount, Bankable senderBank, Accountable recipientAccount, Bankable recipientBank, double moneyValue) {
        return senderBank.transfer(senderAccount, recipientAccount, recipientBank, moneyValue);
    }

    public OperationResult addAddress(String address) {
        for (Bankable bank : _centralBank.getBanks())
            return bank.addClientAddress(_client, address);

        return OperationResult.FAIL;
    }

    public OperationResult addPassportNumber(String passportNumber) {
        for (Bankable bank : _centralBank.getBanks())
            return bank.addClientPassportNumber(_client, passportNumber);

        return OperationResult.FAIL;
    }

    public OperationResult subscribeClient(Bankable bank) {
        return bank.subscribeClient(_client);
    }

    public ArrayList<SavedTransaction> showTransactionHistory(Accountable account) {
        return account.getTransactions();
    }
}
