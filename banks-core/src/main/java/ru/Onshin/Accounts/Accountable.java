package ru.Onshin.Accounts;

import ru.Onshin.Banks.Bankable;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.OperationResult.OperationResult;
import ru.Onshin.Transactions.Transactionable;

import java.util.ArrayList;

/**
 * Интерфейс для представления банковского счета.
 */
public interface Accountable {
    int getId();

    Clientable getOwner();

    Bankable getBank();

    /**
     * Верифицирует счет
     */
    void approveVerification();

    double getBalance();

    /**
     * Снимает деньги со счета
     *
     * @param moneyValue количество денег для снятия
     * @return результат операции
     */
    OperationResult withdraw(double moneyValue);

    /**
     * Пополняет счет
     *
     * @param moneyValue оличество денег для пополнение
     * @return результат операции
     */
    OperationResult refill(double moneyValue);

    /**
     * Добавляет транзакцию в историю транзакций
     * @param transaction транзакция
     */
    void addTransaction(Transactionable transaction);

    /**
     * Удалаяет последнюю транзакцию из истории транзакций
     */
    void deleteLastTransaction();

    ArrayList<SavedTransaction> getTransactions();

    ArrayList<String> getTransactionHistory();

    /**
     * Начисляет ежемесячные проценты/комиссии
     */
    void calculateMonthlyInterest();

    /**
     * Рассчитывает дневные проценты/комиссии
     */
    void calculateDailyInterest();
}
