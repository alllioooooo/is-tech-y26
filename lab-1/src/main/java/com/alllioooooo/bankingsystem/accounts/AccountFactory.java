package com.alllioooooo.bankingsystem.accounts;


import com.alllioooooo.bankingsystem.bank.BankImpl;

public class AccountFactory {

    public static Accountable createAccount(BankImpl bank, String type) {
        switch (type) {
            case "Debit":
                return new DebitAccount(bank.getDebitInterestRate(), bank.getWithdrawalLimitForSuspiciousAccounts());
            case "Deposit":
                return new DepositAccount(10000, System.currentTimeMillis() + 31536000000L, bank.getDepositInterestRate(), bank.getWithdrawalLimitForSuspiciousAccounts());
            case "Credit":
                return new CreditAccount(50000, bank.getCreditCommission(), bank.getWithdrawalLimitForSuspiciousAccounts());
            default:
                throw new IllegalArgumentException("Invalid account type");
        }
    }
}
