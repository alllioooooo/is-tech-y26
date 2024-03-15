package com.alllioooooo.bankingsystem.bank;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.accounts.CreditAccount;
import com.alllioooooo.bankingsystem.clients.Client;
import com.alllioooooo.bankingsystem.notifications.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankImpl implements Bank {
    private final List<Observer> observers = new ArrayList<>();
    private Map<Client, List<Accountable>> clientAccounts = new HashMap<>();

    private double debitInterestRate;
    private double depositInterestRate;
    private double creditCommission;
    private double withdrawalLimitForSuspiciousAccounts;

    public BankImpl(double debitInterestRate, double depositInterestRate, double creditCommission, double withdrawalLimitForSuspiciousAccounts) {
        this.debitInterestRate = debitInterestRate;
        this.depositInterestRate = depositInterestRate;
        this.creditCommission = creditCommission;
        this.withdrawalLimitForSuspiciousAccounts = withdrawalLimitForSuspiciousAccounts;
    }
    public double getDebitInterestRate() {
        return debitInterestRate;
    }

    public double getDepositInterestRate() {
        return depositInterestRate;
    }

    public double getCreditCommission() {
        return creditCommission;
    }

    public double getWithdrawalLimitForSuspiciousAccounts() {
        return withdrawalLimitForSuspiciousAccounts;
    }

    public void registerAccount(Client client, Accountable account) {
        clientAccounts.computeIfAbsent(client, k -> new ArrayList<>()).add(account);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void applyMonthlyInterestAndCommissions() {
        for (Map.Entry<Client, List<Accountable>> entry : clientAccounts.entrySet()) {
            for (Accountable account : entry.getValue()) {
                account.applyMonthlyInterest();
                
                if (account instanceof CreditAccount) {
                    ((CreditAccount) account).applyMonthlyCommission();
                }
            }
        }
    }

}