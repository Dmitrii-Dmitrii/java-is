package ru.Onshin.Commands;

import ru.Onshin.Banks.BalanceDepositInterestPair;
import ru.Onshin.Services.CentralBankService;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "create bank"
)
public class CreateBankCommand implements Callable<Integer> {
    public Integer call() {
        System.out.print("Enter bank id: ");
        var scanner = new Scanner(System.in);
        int bankId = scanner.nextInt();

        System.out.print("Enter debit interest: ");
        scanner = new Scanner(System.in);
        double debitInterest = scanner.nextDouble();

        if (debitInterest < 0) {
            System.out.println("Debit interest can't be less than 0!");
            return null;
        }

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

        System.out.print("Enter commission: ");
        scanner = new Scanner(System.in);
        double commission = scanner.nextDouble();

        if (commission < 0) {
            System.out.println("Commission can't be less than 0!");
            return null;
        }

        System.out.print("Enter verification limit: ");
        scanner = new Scanner(System.in);
        double verificationLimit = scanner.nextDouble();

        if (verificationLimit < 0) {
            System.out.println("Verification limit can't be less than 0!");
            return null;
        }

        CentralBankService centralBankService = new CentralBankService();
        System.out.println(centralBankService.createBank(bankId, debitInterest, depositInterests, commission, verificationLimit));
        return null;
    }
}
