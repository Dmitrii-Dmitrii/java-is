package ru.Onshin;

import ru.Onshin.Banks.BalanceDepositInterestPair;
import ru.Onshin.Banks.Bankable;
import ru.Onshin.CentralBank.CentralBank;
import ru.Onshin.Clients.Clientable;
import ru.Onshin.Commands.*;
import ru.Onshin.Services.*;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    public static void startMenu() {
        System.out.println("Commands:");
        System.out.println("• show account balance");
        System.out.println("• refill account");
        System.out.println("• withdraw account");
        System.out.println("• transfer to account");
        System.out.println("• add address to client");
        System.out.println("• add passport number to client");
        System.out.println("• subscribe to notifications for client");
        System.out.println("• show account transaction history");

        System.out.println("• create client");
        System.out.println("• create debit account");
        System.out.println("• create deposit account");
        System.out.println("• create credit account");
        System.out.println("• update debit interest");
        System.out.println("• update deposit interests");
        System.out.println("• update commission");
        System.out.println("• undo last transaction");

        System.out.println("• create bank");

        System.out.println("• change date");
        System.out.println("• exit");
    }

    public static void main(String[] args) {
        CentralBank _centralBank = CentralBank.getCentralBank();
        ArrayList<BalanceDepositInterestPair> depositInterests = new ArrayList<>();
        depositInterests.add(new BalanceDepositInterestPair(100000,0.1));
        depositInterests.add(new BalanceDepositInterestPair(200000,0.2));
        _centralBank.createBank(1, 0.1, depositInterests, 36500, 100000);
        Bankable bank1 = _centralBank.getBanks().get(0);
        _centralBank.createBank(2, 0.2, depositInterests, 36500, 200000);
        Bankable bank2 = _centralBank.getBanks().get(1);

        bank1.createClient(1, "a", "a", "a", "a");
        Clientable client1 = bank1.getClients().get(0);
        bank1.createDebitAccount(1, client1);

        bank2.createClient(2, "a", "a", "a", "a");
        Clientable client2 = bank2.getClients().get(0);
        bank2.createDebitAccount(2, client2);

        FindService findService = new FindService();

        CommandLine commandLine = new CommandLine(new Command())
                .addSubcommand(new ShowAccountBalanceCommand(findService))
                .addSubcommand(new RefillCommand(findService))
                .addSubcommand(new WithdrawCommand(findService))
                .addSubcommand(new TransferCommand(findService))
                .addSubcommand(new AddAddressCommand(findService))
                .addSubcommand(new AddPassportNumberCommand(findService))
                .addSubcommand(new SubscribeToNotificationsCommand(findService))
                .addSubcommand(new ShowTransactionHistoryCommand(findService))
                .addSubcommand(new CreateClientCommand(findService))
                .addSubcommand(new CreateDebitAccountCommand(findService))
                .addSubcommand(new CreateDepositAccountCommand(findService))
                .addSubcommand(new CreateCreditAccountCommand(findService))
                .addSubcommand(new UpdateDebitInterestCommand(findService))
                .addSubcommand(new UpdateDepositInterestsCommand(findService))
                .addSubcommand(new UpdateCommissionCommand(findService))
                .addSubcommand(new UndoLastTransactionCommand(findService))
                .addSubcommand(new CreateBankCommand())
                .addSubcommand(new ChangeDateCommand())
                .addSubcommand(new ExitCommand());

        Scanner scanner = new Scanner(System.in);
        commandLine.setExecutionStrategy(new CommandLine.RunAll());

        while (true) {
            startMenu();

            var string = scanner.nextLine();
            commandLine.execute(string);
        }
    }
}