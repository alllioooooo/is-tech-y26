package com.alllioooooo.bankingsystem.services;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;

public class DepositAccountService {
    private Accountable account;

    public DepositAccountService(Accountable account) {
        this.account = account;
    }

    public void applyInterest(InterestCalculationStrategy strategy, double interestRate) {
        double interest = strategy.calculateInterest(account.getBalance(), interestRate);
        try {
            account.deposit(interest);
        } catch (InvalidOperationException e) {
            System.out.println("Invalid operation while applying interest: " + e.getMessage());
        }
    }
}