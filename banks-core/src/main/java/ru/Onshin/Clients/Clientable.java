package ru.Onshin.Clients;

import ru.Onshin.ClientNotifiers.Notiferable;

import java.util.ArrayList;

public interface Clientable {
    int getId();
    String getAddress();
    String getPassportNumber();
    void setAddress(String address);
    void setPassportNumber(String passportNumber);
    ArrayList<Notiferable> getClientNotifiers();
    void addClientNotifiers(Notiferable clientNotifier);
}
