package com.alllioooooo.bankingsystem.accounts;

import com.alllioooooo.bankingsystem.exceptions.InsufficientFundsException;
import com.alllioooooo.bankingsystem.exceptions.InvalidOperationException;
import com.alllioooooo.bankingsystem.exceptions.UnauthorizedWithdrawalException;

public interface Accountable {
    void deposit(double amount) throws InvalidOperationException;
    boolean withdraw(double amount) throws InvalidOperationException, InsufficientFundsException, UnauthorizedWithdrawalException;
    void calculateInterest();
    void applyMonthlyInterest();
    void setSuspicious(boolean isSuspicious);
    boolean isSuspicious();
    double getBalance();
}
