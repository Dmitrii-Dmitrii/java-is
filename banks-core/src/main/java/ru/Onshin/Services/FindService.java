package ru.Onshin.Services;

import ru.Onshin.Accounts.Accountable;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.CentralBank.CentralBank;
import ru.Onshin.Clients.Clientable;

public class FindService {
    private final CentralBank _centralBank;

    public FindService() {
        _centralBank = CentralBank.getCentralBank();
    }

    public Clientable findClient(int clientId) {
        for (Bankable bank : _centralBank.getBanks())
            for (Clientable client : bank.getClients())
                if (client.getId() == clientId)
                    return client;

        return null;
    }

    public Bankable findBank(int bankId) {
        for (Bankable bank : _centralBank.getBanks())
            if (bank.getId() == bankId)
                return bank;

        return null;
    }

    public Accountable findAccount(int accountId, int bankId) {
        for (Bankable bank : _centralBank.getBanks())
            if (bank.getId() == bankId)
                for (Accountable account : bank.getAccounts())
                    if (account.getId() == accountId)
                        return account;

        return null;
    }
}
