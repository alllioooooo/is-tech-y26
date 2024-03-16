package com.alllioooooo.bankingsystem.services;

import com.alllioooooo.bankingsystem.notifications.Notificationable;

public class NotificationService {
    private Notificationable notificationMethod;

    public NotificationService(Notificationable notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    public void notifyCustomer(String message) {
        notificationMethod.sendNotification(message);
    }
}
