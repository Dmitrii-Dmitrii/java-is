package ru.Onshin.Commands;

import ru.Onshin.Banks.Bankable;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.Services.BankService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "create deposit account"
)
public class CreateDepositAccountCommand implements Callable<Integer> {
    private FindService _findService;

    public CreateDepositAccountCommand(FindService findService) {
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

        System.out.print("Enter term: ");
        scanner = new Scanner(System.in);
        int term = scanner.nextInt();

        if (term < 0) {
            System.out.println("Term can't be less than 0!");
            return null;
        }

        System.out.println(bankService.createDepositAccount(accountId, term, client));
        return null;
    }
}