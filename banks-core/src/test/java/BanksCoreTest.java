import ru.Onshin.Accounts.Accountable;
import ru.Onshin.Banks.BalanceDepositInterestPair;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.CentralBank.CentralBank;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.Clock.Clock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BanksCoreTest {
    private CentralBank _centralBank;
    private Clock _clock;
    private ArrayList<BalanceDepositInterestPair> _depositInterests;
    private Bankable _bank1;
    private Bankable _bank2;
    private Clientable _client1;
    private Clientable _client2;
    private Accountable _account1;
    private Accountable _account2;

    @Test
    public void testUndoRefill() {
        createModels();
        double moneyValue = 1000;

        _bank1.withdraw(_account1, _account1.getBalance());

        _bank1.refill(_account1, moneyValue);
        _bank1.undoTransaction(_account1);

        assertEquals(0, _account1.getBalance());
    }

    @Test
    public void testTransferBetweenBanks() {
        createModels();
        double moneyValue = 1000;

        _bank1.withdraw(_account1, _account1.getBalance());
        _bank2.withdraw(_account2, _account2.getBalance());

        _bank1.refill(_account1, moneyValue);
        _bank1.transfer(_account1, _account2, _bank2, moneyValue);

        assertEquals(0, _account1.getBalance());
        assertEquals(moneyValue, _account2.getBalance());
    }

    @Test
    public void testUndoTransfer() {
        createModels();
        double moneyValue = 1000;

        _bank1.withdraw(_account1, _account1.getBalance());
        _bank2.withdraw(_account2, _account2.getBalance());

        _bank1.refill(_account1, moneyValue);
        _bank1.transfer(_account1, _account2, _bank2, moneyValue);
        _bank1.undoTransaction(_account1);

        assertEquals(moneyValue, _account1.getBalance());
        assertEquals(0, _account2.getBalance());
    }

    public void createModels() {
        _centralBank = CentralBank.getCentralBank();
        _clock = Clock.getClock();
        _depositInterests = new ArrayList<>();
        _depositInterests.add(new BalanceDepositInterestPair(100000, 0.1));
        _depositInterests.add(new BalanceDepositInterestPair(200000, 0.2));
        _centralBank.createBank(1, 0.365, _depositInterests, 36500, 100000);
        _bank1 = _centralBank.getBanks().get(0);
        _centralBank.createBank(2, 0.2, _depositInterests, 36500, 200000);
        _bank2 = _centralBank.getBanks().get(1);

        _bank1.createClient(1, "a", "a", "a", "a");
        _client1 = _bank1.getClients().get(0);
        _bank1.createDebitAccount(1, _client1);
        _account1 = _bank1.getAccounts().get(0);

        _bank2.createClient(2, "b", "b", "b", "b");
        _client2 = _bank2.getClients().get(0);
        _bank2.createDebitAccount(2, _client2);
        _account2 = _bank2.getAccounts().get(0);
    }
}
