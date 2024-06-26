package ru.Onshin.Accounts;

import ru.Onshin.Transactions.Transactionable;

import java.lang.reflect.Type;

public record SavedTransaction(Transactionable transaction, Type transactionType, double moneyValue) {
}
