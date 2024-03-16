package com.alllioooooo.bankingsystem.services;

public interface InterestCalculationStrategy {
    double calculateInterest(double balance, double interestRate);
}
