package com.alllioooooo.bankingsystem.accounts;

import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;
import com.alllioooooo.bankingsystem.exceptions.UnauthorizedWithdrawalException;

public class CreditAccount implements Accountable {
    private double balance;
    private final double creditLimit;
    private final double commission;
    private boolean isSuspicious;
    private double withdrawalLimitForSuspiciousAccounts;

    public CreditAccount(double creditLimit, double commission, double withdrawalLimitForSuspiciousAccounts) {
        this.creditLimit = creditLimit;
        this.commission = commission;
        this.balance = 0;
        this.withdrawalLimitForSuspiciousAccounts = withdrawalLimitForSuspiciousAccounts;
    }

    @Override
    public void deposit(double amount) throws InvalidOperationException {
        if (amount <= 0) {
            throw new InvalidOperationException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    @Override
    public boolean withdraw(double amount) throws InvalidOperationException, InsufficientFundsException, UnauthorizedWithdrawalException {
        // assert amount >= 0 && balance + creditLimit < amount;
        if (amount <= 0) {
            throw new InvalidOperationException("Withdraw amount must be greater than zero.");
        }

        if (isSuspicious && amount > withdrawalLimitForSuspiciousAccounts) {
            throw new UnauthorizedWithdrawalException("Withdrawal limit exceeded for suspicious account.");
        }

        if (balance + creditLimit < amount) {
            throw new InsufficientFundsException("Insufficient funds.");
        }

        balance -= amount;
        return true;
    }


    // NOTE: maybe default method in interface
    @Override
    public void calculateInterest() {
        if (balance < 0) {
            balance -= commission;
        }
    }

    @Override
    public void setSuspicious(boolean isSuspicious) {
        this.isSuspicious = isSuspicious;
    }

    @Override
    public boolean isSuspicious() {
        return isSuspicious;
    }

    public double getBalance() {
        return balance;
    }

    public void applyMonthlyInterest() {
        if (balance < 0) {
            balance -= commission;
        }
    }

    public void applyMonthlyCommission() {
        if (balance < 0) {
            balance -= commission;
        }
    }
}
