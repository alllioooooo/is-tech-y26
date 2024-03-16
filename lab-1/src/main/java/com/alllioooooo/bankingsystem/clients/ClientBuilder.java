package com.alllioooooo.bankingsystem.clients;

import java.util.Objects;

public class ClientBuilder {
    String name;
    String surname;
    String address;
    String passportNumber;

    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ClientBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public ClientBuilder setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public Client build() {
        // NOTE: Objects.requireNonNull()
        if (name == null || surname == null) {
            throw new IllegalStateException("Name and surname must not be null");
        }

        return new Client(this);
    }
}