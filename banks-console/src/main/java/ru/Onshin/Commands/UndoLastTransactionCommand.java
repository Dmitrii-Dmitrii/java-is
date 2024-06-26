package ru.Onshin.Commands;

import ru.Onshin.Accounts.Accountable;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.Services.BankService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "undo last transaction"
)
public class UndoLastTransactionCommand implements Callable<Integer> {
    private FindService _findService;

    public UndoLastTransactionCommand(FindService findService) {
        _findService = findService;
    }

    public Integer call() {
        System.out.print("Enter bank id: ");
        var scanner = new Scanner(System.in);
        int bankId = scanner.nextInt();

        Bankable bank = _findService.findBank(bankId);

        if (bank == null) {
            System.out.println("Bank doesn't exists!");
            return null;
        }

        System.out.print("Enter account id: ");
        scanner = new Scanner(System.in);
        int accountId = scanner.nextInt();

        Accountable account = _findService.findAccount(accountId, bankId);

        if (account == null) {
            System.out.println("Account doesn't exists!");
            return null;
        }

        BankService bankService = new BankService(bank);

        System.out.println(bankService.undoTransaction(account));
        return null;
    }
}
