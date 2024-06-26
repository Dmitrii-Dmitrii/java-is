package ru.Onshin.CentralBank;

import ru.Onshin.Accounts.Accountable;
import ru.Onshin.Banks.BalanceDepositInterestPair;
import ru.Onshin.Banks.Bank;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.OperationResult.OperationResult;
import ru.Onshin.Transactions.Transactionable;
import ru.Onshin.Transactions.Transfer;

import java.util.ArrayList;

public class CentralBank {
    private ArrayList<Bankable> _banks;
    private static CentralBank _centralBank;

    private CentralBank() {
        _banks = new ArrayList<>();
    }

    public static CentralBank getCentralBank() {
        if (_centralBank == null)
            _centralBank = new CentralBank();

        return _centralBank;
    }

    public ArrayList<Bankable> getBanks() { return _banks; }

    /**
     * Создает банк
     *
     * @param id индентификатор банка
     * @param debitInterest процент для дебетого счета
     * @param depositInterests проценты для депозитного счета
     * @param commission комиссия для кредитного счета
     * @param verificationLimit лимит для неверифицированных счетов
     * @return результат операции
     */
    public OperationResult createBank(int id, double debitInterest, ArrayList<BalanceDepositInterestPair> depositInterests, double commission, double verificationLimit) {
        for (Bankable bank : _banks)
            if (bank.getId() == id)
                return OperationResult.FAIL;

        _banks.add(new Bank(id, debitInterest, depositInterests, commission, verificationLimit));
        return OperationResult.SUCCESS;
    }

    /**
     * Осуществляет переводы между счетами
     *
     * @param senderAccount счет отправителя
     * @param senderBank банк отправителя
     * @param recipientAccount счет получателя
     * @param recipientBank банк получателя
     * @param moneyValue размер перевода
     * @return результат операции
     */
    public OperationResult transfer(Accountable senderAccount, Bankable senderBank, Accountable recipientAccount, Bankable recipientBank, double moneyValue) {
        boolean flag = false;

        for (Accountable account : senderBank.getAccounts())
            if (account.getId() == senderAccount.getId()) {
                flag = true;
                break;
            }

        if (!flag)
            return OperationResult.FAIL;

        for (Accountable account : recipientBank.getAccounts())
            if (account.getId() == recipientAccount.getId()) {
                flag = false;
                break;
            }

        if (flag)
            return OperationResult.FAIL;

        Transactionable transaction = new Transfer(moneyValue, recipientAccount, senderBank, recipientBank);
        senderAccount.addTransaction(transaction);
        recipientAccount.addTransaction(transaction);
        return transaction.execute(senderAccount);
    }

    /**
     * Отменяет перевод
     *
     * @param senderAccount счет отправителя
     * @param transfer транзакция перевода
     * @return результат операции
     */
    public OperationResult undoTransfer(Accountable senderAccount, Transfer transfer) {
        senderAccount.deleteLastTransaction();
        transfer.getRecipientAccount().deleteLastTransaction();

        return transfer.undo(senderAccount);
    }

    /**
     * Уведомление банка о начислении ежемесячных процентов/комисий
     */
    public void calculateMonthlyInterest() {
        for (Bankable bank : _banks)
            bank.calculateMonthlyInterest();
    }

    /**
     * Уведомление банка о рассчете ежедневных процентов/комисий
     */
    public void calculateDailyInterest() {
        for (Bankable bank : _banks)
            bank.calculateDailyInterest();
    }
}
