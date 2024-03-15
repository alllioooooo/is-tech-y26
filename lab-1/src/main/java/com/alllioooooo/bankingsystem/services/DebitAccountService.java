package com.alllioooooo.bankingsystem.services;

import com.alllioooooo.bankingsystem.accounts.Accountable;
import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;

public class DebitAccountService {
    private Accountable account;

    public DebitAccountService(Accountable account) {
        this.account = account;
    }

    public void applyMonthlyFee(double fee) {
        try {
            if (account.getBalance() > fee) {
                account.withdraw(fee);
            } else {
                System.out.println("Insufficient funds to apply the monthly fee.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds to apply the monthly fee.");
        } catch (Exception e) {
            System.out.println("Error applying monthly fee: " + e.getMessage());
        }
    }
}