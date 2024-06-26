package ru.Onshin.Commands;

import ru.Onshin.Banks.Bankable;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.Services.BankService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "create debit account"
)
public class CreateDebitAccountCommand implements Callable<Integer> {
    private FindService _findService;

    public CreateDebitAccountCommand(FindService findService) {
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

        BankService bankService = new BankService(bank);

        System.out.print("Enter client id: ");
        scanner = new Scanner(System.in);
        int clientId = scanner.nextInt();

        Clientable client = _findService.findClient(clientId);

        if (client == null) {
            System.out.println("Client doesn't exists!");
            return null;
        }

        System.out.print("Enter account id: ");
        scanner = new Scanner(System.in);
        int accountId = scanner.nextInt();

        System.out.println(bankService.createDebitAccount(accountId, client));
        return null;
    }
}
