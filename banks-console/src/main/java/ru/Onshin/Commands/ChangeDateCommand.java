package ru.Onshin.Commands;

import ru.Onshin.Services.ClockService;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "change date"
)
public class ChangeDateCommand implements Callable<Integer> {

    public Integer call() {
        System.out.print("Enter amount of days: ");
        var scanner = new Scanner(System.in);
        int daysAmount = scanner.nextInt();

        if (daysAmount < 0) {
            System.out.println("Amount of days can't be less than 0!");
            return null;
        }

        ClockService clockService = new ClockService();
        clockService.rewindDate(daysAmount);
        return null;
    }
}
