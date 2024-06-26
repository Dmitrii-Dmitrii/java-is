package ru.Onshin.Commands;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "exit"
)
public class ExitCommand implements Callable<Integer> {
    public Integer call() {
        System.exit(0);
        return null;
    }
}
