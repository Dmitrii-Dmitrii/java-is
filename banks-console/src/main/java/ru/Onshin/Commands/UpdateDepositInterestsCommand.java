package ru.Onshin.Commands;

import ru.Onshin.Banks.BalanceDepositInterestPair;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.Services.BankService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "update deposit interests"
)
public class UpdateDepositInterestsCommand implements Callable<Integer> {
    private FindService _findService;

    public UpdateDepositInterestsCommand(FindService findService) {
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

        System.out.print("Enter amount of interests: ");
        scanner = new Scanner(System.in);
        double interestAmount = scanner.nextDouble();

        if (interestAmount < 0) {
            System.out.println("Amount of interests can't be less than 0!");
            return null;
        }

        int cnt = 1;
        ArrayList<BalanceDepositInterestPair> depositInterests = new ArrayList<>();

        while (cnt <= interestAmount) {
            System.out.print("Enter deposit interest: ");
            scanner = new Scanner(System.in);
            double depositInterest = scanner.nextDouble();

            if (depositInterest < 0) {
                System.out.println("Deposit interest can't be less than 0!");
                return null;
            }

            System.out.print("Enter deposit interest balance: ");
            scanner = new Scanner(System.in);
            double depositInterestBalance = scanner.nextDouble();

            if (depositInterestBalance < 0) {
                System.out.println("Deposit interest balance can't be less than 0!");
                return null;
            }

            depositInterests.add(new BalanceDepositInterestPair(depositInterestBalance, depositInterest));
            ++cnt;
        }

        bankService.setDepositInterests(depositInterests);
        System.out.println("Deposit interests have set.");
        return null;
    }
}

