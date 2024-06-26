package ru.Onshin.Commands;

import ru.Onshin.Clients.Clientable;
import ru.Onshin.Services.ClientService;
import ru.Onshin.Services.FindService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "add address to client"
)
public class AddAddressCommand implements Callable<Integer> {
    private FindService _findService;

    public AddAddressCommand(FindService findService) {
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

        System.out.print("Enter address: ");
        scanner = new Scanner(System.in);
        String address = scanner.nextLine();

        System.out.println(clientService.addAddress(address));
        return null;
    }
}