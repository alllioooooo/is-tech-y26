package com.alllioooooo.bankingsystem.clients;

public class Client implements Clientable {
    private final String name;
    private final String surname;
    private final String address;
    private final String passportNumber;

    protected Client(ClientBuilder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.address = builder.address;
        this.passportNumber = builder.passportNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPassportNumber() {
        return passportNumber;
    }
}
