package com.alllioooooo.bankingsystem.services;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.accounts.CreditAccount;
import com.alllioooooo.bankingsystem.bank.BankImpl;
import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;

public class CreditAccountService {
    private Accountable account;
    private BankImpl bank;

    public CreditAccountService(Accountable account, BankImpl bank) {
        this.account = account;
        this.bank = bank;
    }

    public void applyCommission() {
        if (account instanceof CreditAccount) {
            double commission = bank.getCreditCommission();
            try {
                account.withdraw(commission);
            } catch (InsufficientFundsException e) {
                System.out.println("Insufficient funds to apply commission.");
            } catch (Exception e) {
                System.out.println("Error applying commission: " + e.getMessage());
            }
        }
    }
}