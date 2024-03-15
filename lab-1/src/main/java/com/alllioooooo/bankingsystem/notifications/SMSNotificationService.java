package com.alllioooooo.bankingsystem.notifications;

public class SMSNotificationService implements Notificationable {
    @Override
    public void sendNotification(String message) {
        System.out.println("SMS sent with message: " + message);
    }
}