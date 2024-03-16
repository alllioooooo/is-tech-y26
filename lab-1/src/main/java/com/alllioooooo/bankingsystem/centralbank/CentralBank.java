package com.alllioooooo.bankingsystem.centralbank;

import com.alllioooooo.bankingsystem.bank.Bank;

import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private final List<Bank> registeredBanks;

    public CentralBank() {
        this.registeredBanks = new ArrayList<>();
    }

    public void registerBank(Bank bank) {
        if (!registeredBanks.contains(bank)) {
            registeredBanks.add(bank);
            System.out.println("Bank registered successfully.");
        } else {
            System.out.println("Bank is already registered.");
        }
    }

    public void transferBetweenBanks(Bank fromBank, Bank toBank, double amount) {
        // тут нужно добавить логику перевода между разными банками но как то не могу придумать
        System.out.println("Transfer completed.");
    }

    public void notifyBanks(String message) {
        for (Bank bank : registeredBanks) {
            bank.notifyObservers(message);
        }
    }
}

