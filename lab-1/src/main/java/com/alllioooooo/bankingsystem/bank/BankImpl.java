package com.alllioooooo.bankingsystem.bank;

import com.alllioooooo.bankingsystem.notifications.Observer;

import java.util.ArrayList;
import java.util.List;

public class BankImpl implements Bank {
    private final List<Observer> observers;

    {
        observers = new ArrayList<>();
    }

    public BankImpl() {
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}