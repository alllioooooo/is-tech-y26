package com.alllioooooo.bankingsystem.accounts;

import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;
import com.alllioooooo.bankingsystem.exceptions.UnauthorizedWithdrawalException;

public class DepositAccount implements Accountable {
    private double balance;
    private final double interestRate;
    private final long termEnds;
    private boolean isSuspicious;
    private double withdrawalLimitForSuspiciousAccounts;
    private double monthlyInterestAccumulated = 0;

    public DepositAccount(double initialBalance, long termEnds, double depositInterestRate, double withdrawalLimitForSuspiciousAccounts) {
        this.balance = initialBalance;
        this.termEnds = termEnds;
        this.interestRate = depositInterestRate;
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
        if (System.currentTimeMillis() < termEnds) {
            throw new InvalidOperationException("Cannot withdraw before term ends.");
        }
        if (amount <= 0) {
            throw new InvalidOperationException("Withdraw amount must be greater than zero.");
        }
        if (isSuspicious && amount > withdrawalLimitForSuspiciousAccounts) {
            throw new UnauthorizedWithdrawalException("Withdrawal limit exceeded for suspicious account.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
        balance -= amount;
        return true;
    }

    @Override
    public void calculateInterest() {
        if (System.currentTimeMillis() >= termEnds) {
            double dailyInterest = balance * (interestRate / 365);
            monthlyInterestAccumulated += dailyInterest;
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
        if (System.currentTimeMillis() >= termEnds) {
            balance += monthlyInterestAccumulated;
            monthlyInterestAccumulated = 0;
        }
    }
}
