package ru.Onshin.Commands;

import ru.Onshin.Banks.Bankable;
import ru.Onshin.Services.BankService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "create client"
)
public class CreateClientCommand implements Callable<Integer> {
    private FindService _findService;

    public CreateClientCommand(FindService findService) {
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

        System.out.print("Enter name: ");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        if (Objects.equals(name, "")) {
            System.out.println("Name can't be null!");
            return null;
        }

        System.out.print("Enter last name: ");
        scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();

        if (Objects.equals(lastName, "")) {
            System.out.println("Last name can't be null!");
            return null;
        }

        System.out.print("Enter address: ");
        scanner = new Scanner(System.in);
        String address = scanner.nextLine();

        System.out.print("Enter passport number: ");
        scanner = new Scanner(System.in);
        String passportNumber = scanner.nextLine();

        System.out.println(bankService.createClient(clientId, name, lastName, address, passportNumber));
        return null;
    }
}
