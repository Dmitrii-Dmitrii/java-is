package ru.Onshin.Commands;

import ru.Onshin.Banks.Bankable;
import ru.Onshin.Services.BankService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "update commission"
)
public class UpdateCommissionCommand implements Callable<Integer> {
    private FindService _findService;

    public UpdateCommissionCommand(FindService findService) {
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

        System.out.print("Enter commission: ");
        scanner = new Scanner(System.in);
        double commission = scanner.nextDouble();

        if (commission < 0) {
            System.out.println("Commission can't be less than 0!");
            return null;
        }

        bankService.setCommission(commission);
        System.out.println("Debit interest has set.");
        return null;
    }
}
