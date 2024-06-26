package ru.Onshin.Commands;

import ru.Onshin.Banks.Bankable;
import ru.Onshin.Services.BankService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "update debit interest"
)
public class UpdateDebitInterestCommand implements Callable<Integer> {
    private FindService _findService;

    public UpdateDebitInterestCommand(FindService findService) {
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

        System.out.print("Enter debit interest: ");
        scanner = new Scanner(System.in);
        double debitInterest = scanner.nextDouble();

        if (debitInterest < 0) {
            System.out.println("Debit interest can't be less than 0!");
            return null;
        }

        bankService.setDebitInterest(debitInterest);
        System.out.println("Debit interest has set.");
        return null;
    }
}
