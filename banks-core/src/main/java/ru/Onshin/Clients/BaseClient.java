package ru.Onshin.Clients;

import ru.Onshin.ClientNotifiers.Notiferable;

import java.util.ArrayList;

public class BaseClient implements Clientable {
    private int _id;
    private final String _name;
    private final String _lastName;
    private String _address;
    private String _passportNumber;
    private ArrayList<Notiferable> _clientNotifiers;

    public BaseClient(int id, String name, String lastName, String address, String passportNumber) {
        _id = id;
        _name = name;
        _lastName = lastName;
        _address = address;
        _passportNumber = passportNumber;
        _clientNotifiers = new ArrayList<>();
    }

    public int getId() { return _id; }

    public String getAddress() { return _address; }

    public String getPassportNumber() { return _passportNumber; }

    public void setAddress(String address) { _address = address; }

    public void setPassportNumber(String passportNumber) { _passportNumber = passportNumber; }

    public ArrayList<Notiferable> getClientNotifiers() { return _clientNotifiers; }

    public void addClientNotifiers(Notiferable clientNotifier) { _clientNotifiers.add(clientNotifier); }

    public static class ClientBuilder {
        private int _id;
        private String _name;
        private String _lastName;
        private String _address;
        private String _passportNumber;

        public ClientBuilder addId(int id) {
            _id = id;
            return this;
        }

        public ClientBuilder addName(String name) {
            _name = name;
            return this;
        }

        public ClientBuilder addLastName(String lastName) {
            _lastName = lastName;
            return this;
        }

        public ClientBuilder addAddress(String address) {
            _address = address;
            return this;
        }

        public ClientBuilder addPassportNumber(String passportNumber) {
            _passportNumber = passportNumber;
            return this;
        }

        public BaseClient build() {
            if (_id <= 0) throw new IllegalArgumentException();
            if (_name == null) throw new IllegalArgumentException();
            if (_lastName == null) throw new IllegalArgumentException();

            return new BaseClient(_id, _name, _lastName, _address, _passportNumber);
        }
    }
}
