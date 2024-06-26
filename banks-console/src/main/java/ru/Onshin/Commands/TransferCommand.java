package ru.Onshin.Commands;

import ru.Onshin.Accounts.Accountable;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.Services.ClientService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "transfer to account"
)
public class TransferCommand implements Callable<Integer> {
    private FindService _findService;

    public TransferCommand(FindService findService) {
        _findService = findService;
    }

    public Integer call() {
        System.out.print("Enter sender bank id: ");
        var scanner = new Scanner(System.in);
        int senderBankId = scanner.nextInt();

        Bankable senderBank = _findService.findBank(senderBankId);

        if (senderBank == null) {
            System.out.println("Bank doesn't exists!");
            return null;
        }

        System.out.print("Enter sender account id: ");
        scanner = new Scanner(System.in);
        int senderAccountId = scanner.nextInt();

        Accountable senderAccount = _findService.findAccount(senderAccountId, senderBankId);

        if (senderAccount == null) {
            System.out.println("Account doesn't exists!");
            return null;
        }

        System.out.print("Enter recipient bank id: ");
        scanner = new Scanner(System.in);
        int recipientBankId = scanner.nextInt();

        Bankable recipientBank = _findService.findBank(recipientBankId);

        if (recipientBank == null) {
            System.out.println("Bank doesn't exists!");
            return null;
        }

        System.out.print("Enter recipient account id: ");
        scanner = new Scanner(System.in);
        int recipientAccountId = scanner.nextInt();

        Accountable recipientAccount = _findService.findAccount(recipientAccountId, recipientBankId);

        if (recipientAccount == null) {
            System.out.println("Account doesn't exists!");
            return null;
        }

        System.out.print("Enter money value: ");
        scanner = new Scanner(System.in);
        int moneyValue = scanner.nextInt();

        if (moneyValue < 0) {
            System.out.println("Value can't be less than 0!");
            return null;
        }

        ClientService clientService = new ClientService(senderAccount.getOwner());

        System.out.println(clientService.transfer(senderAccount, senderBank, recipientAccount, recipientBank, moneyValue));
        return null;
    }
}