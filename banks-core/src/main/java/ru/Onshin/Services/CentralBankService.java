package ru.Onshin.Services;

import ru.Onshin.Banks.BalanceDepositInterestPair;
import ru.Onshin.CentralBank.CentralBank;
import ru.Onshin.OperationResult.OperationResult;

import java.util.ArrayList;

public class CentralBankService {
    private CentralBank _centralBank;

    public CentralBankService() {
        _centralBank = CentralBank.getCentralBank();
    }

    public OperationResult createBank(int id, double debitInterest, ArrayList<BalanceDepositInterestPair> depositInterests, double commission, double verificationLimit) {
        return _centralBank.createBank(id, debitInterest, depositInterests, commission, verificationLimit);
    }
}
