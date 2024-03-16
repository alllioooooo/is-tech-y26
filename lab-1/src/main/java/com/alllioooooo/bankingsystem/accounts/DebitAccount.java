package com.alllioooooo.bankingsystem.accounts;

import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;
import com.alllioooooo.bankingsystem.exceptions.UnauthorizedWithdrawalException;

public class DebitAccount implements Accountable {
    private double balance;
    private final double interestRate;
    private boolean isSuspicious;
    private double withdrawalLimitForSuspiciousAccounts;
    private double monthlyInterestAccumulated = 0;

    public DebitAccount(double interestRate, double withdrawalLimitForSuspiciousAccounts) {
        this.interestRate = interestRate;
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

    public void calculateInterest() {
        double dailyInterest = balance * (interestRate / 365);
        monthlyInterestAccumulated += dailyInterest;
    }

    public void applyMonthlyInterest() {
        balance += monthlyInterestAccumulated;
        monthlyInterestAccumulated = 0;
    }
}
