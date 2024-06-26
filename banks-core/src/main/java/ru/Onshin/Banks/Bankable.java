package ru.Onshin.Banks;

import ru.Onshin.Accounts.*;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.OperationResult.OperationResult;

import java.util.ArrayList;

/**
 * Интерфейс для предоставления банка.
 */
public interface Bankable {
    int getId();

    ArrayList<Accountable> getAccounts();

    double getDebitInterest();

    ArrayList<BalanceDepositInterestPair> getDepositInterests();

    double getCommission();

    ArrayList<Clientable> getClients();

    /**
     * Подписывает клиенты на увеомления об изменениях по счетам
     *
     * @param client клиент, который подписывается на уведомления от банка
     * @return результат операции
     */
    OperationResult subscribeClient(Clientable client);

    void setDebitInterest(double debitInterest);

    void setDepositInterest(ArrayList<BalanceDepositInterestPair> depositInterests);

    void setCommission(double commission);

    /**
     * Создает дебетовый счет
     *
     * @param id индентификатор счета
     * @param owner владелец счета
     * @return результат операции
     */
    OperationResult createDebitAccount(int id, Clientable owner);

    /**
     * Создает депозитный счет
     *
     * @param id индентификатор счета
     * @param term год, до которого действует счет
     * @param owner владелец счета
     * @return результат операции
     */
    OperationResult createDepositAccount(int id, int term, Clientable owner);

    /**
     * Создает кредитный счет
     *
     * @param id индентификатор счета
     * @param limit кредитный лимит
     * @param owner владелец счета
     * @return результат операции
     */
    OperationResult createCreditAccount(int id, double limit, Clientable owner);

    /**
     * Создает клиента
     *
     * @param id индентификатор клиента
     * @param name имя клиента
     * @param lastName фамилия клиента
     * @param address адрес клиента
     * @param passportNumber номер паспорта клиента
     * @return результат операции
     */
    OperationResult createClient(int id, String name, String lastName, String address, String passportNumber);

    /**
     * Добавляет адрес клиента
     *
     * @param client клиент
     * @param address адрес клиента
     * @return результат операции
     */
    OperationResult addClientAddress(Clientable client, String address);

    /**
     * Добавляет номер паспорта клиента
     *
     * @param client клиент
     * @param passportNumber номер паспорта клиента
     * @return результат операции
     */
    OperationResult addClientPassportNumber(Clientable client, String passportNumber);

    /**
     * Осуществляет пополнение счета
     *
     * @param account счет
     * @param moneyValue количество денег для пополнения
     * @return результат операции
     */
    OperationResult refill(Accountable account, double moneyValue);

    /**
     * Осуществляет снятие со счета
     *
     * @param account счет
     * @param moneyValue количество денег для снятия
     * @return результат операции
     */
    OperationResult withdraw(Accountable account, double moneyValue);

    /**
     * Осуществляет перевод между счетами
     *
     * @param senderAccount счет отправителя
     * @param recipientAccount счет получателя
     * @param recipientBank банк получателя
     * @param moneyValue количество денег для перевода
     * @return результат операции
     */
    OperationResult transfer(Accountable senderAccount, Accountable recipientAccount, Bankable recipientBank, double moneyValue);

    /**
     * Отменяет транзакцию
     *
     * @param account счет, для которого нужно отменить транзакцию
     * @return результат операции
     */
    OperationResult undoTransaction(Accountable account);

    /**
     * Начисляет ежемесячные проценты/комиссии
     */
    void calculateMonthlyInterest();

    /**
     * Рассчитывает дневные проценты/комиссии
     */
    void calculateDailyInterest();

    /**
     * Уведомляет клиентов об изменениях условий счетов
     *
     * @param context сообщение пользователю
     */
    void notifyClients(String context);
}
