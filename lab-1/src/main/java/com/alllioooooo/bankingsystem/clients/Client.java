package com.alllioooooo.bankingsystem.clients;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.validators.SuspiciousAccountValidator;

import java.util.ArrayList;
import java.util.List;

public class Client implements Clientable {
    private final String name;
    private final String surname;
    private String address;
    private String passportNumber;

    // NOTE: подумай что наследники наверно тоже могут тут быть
    private List<Accountable> accounts = new ArrayList<>();

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

    public void setAddress(String address) {
        this.address = address;
        revalidateAccounts();
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        revalidateAccounts();
    }

    public void addAccount(Accountable account) {
        accounts.add(account);
        SuspiciousAccountValidator.validateAccount(account, this);
    }

    private void revalidateAccounts() {
        for (Accountable account : accounts) {
            SuspiciousAccountValidator.validateAccount(account, this);
        }
    }
}
