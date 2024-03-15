package com.alllioooooo.bankingsystem.exceptions;


public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}