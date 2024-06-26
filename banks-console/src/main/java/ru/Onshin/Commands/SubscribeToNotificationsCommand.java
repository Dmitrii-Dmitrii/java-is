package ru.Onshin.Commands;

import ru.Onshin.Banks.Bankable;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.Services.ClientService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "subscribe to notifications for client"
)
public class SubscribeToNotificationsCommand implements Callable<Integer> {
    private FindService _findService;

    public SubscribeToNotificationsCommand(FindService findService) {
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

        System.out.print("Enter bank id: ");
        scanner = new Scanner(System.in);
        int bankId = scanner.nextInt();

        Bankable bank = _findService.findBank(bankId);

        if (bank == null) {
            System.out.println("Bank doesn't exists!");
            return null;
        }

        System.out.println(clientService.subscribeClient(bank));
        return null;
    }
}
