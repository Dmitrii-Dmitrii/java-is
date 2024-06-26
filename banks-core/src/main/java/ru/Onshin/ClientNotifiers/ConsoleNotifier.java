package ru.Onshin.ClientNotifiers;

public class ConsoleNotifier implements Notiferable {
    public void notifyClient(String context) {
        System.out.println(context);
    }
}
