package com.alllioooooo.bankingsystem.notifications;

public class ClientObserver implements Observer {
    private Notificationable notificationService;

    public ClientObserver(Notificationable notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void update(String message) {
        notificationService.sendNotification(message);
    }
}
