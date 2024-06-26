package ru.Onshin.Commands;

import ru.Onshin.Clients.Clientable;
import ru.Onshin.Services.ClientService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "add passport number to client"
)
public class AddPassportNumberCommand implements Callable<Integer> {
    private FindService _findService;

    public AddPassportNumberCommand(FindService findService) {
        _findService = findService;
    }

    public Integer call() {
        System.out.print("Enter owner id: ");
        var scanner = new Scanner(System.in);
        int ownerId = scanner.nextInt();
        Clientable client = _findService.findClient(ownerId);

        if (client == null) {
            System.out.println("Client doesn't exists!");
            return null;
        }

        ClientService clientService = new ClientService(client);

        System.out.print("Enter passport number: ");
        scanner = new Scanner(System.in);
        String passportNumber = scanner.nextLine();

        System.out.println(clientService.addPassportNumber(passportNumber));
        return null;
    }
}