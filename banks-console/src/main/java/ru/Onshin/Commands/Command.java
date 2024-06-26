package ru.Onshin.Commands;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = ""
)
public class Command implements Callable<Integer> {
    public Integer call() {
        return null;
    }
}
