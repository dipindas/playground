package com.demo.playground.util.lowleveldesign.solid.dependencyinversion.good;

public class NotificationMain {
    /**
     * DIP:
     * High level modules shouldn't be dependent on the low level modules, both should be dependent on Abstraction
     *
     * This is an example for Open-close principle as well
     */
    public static void main(String[] args) {
        NotificationSenderService service = new NotificationSenderService();
        service.sendNotification(new EmailNotification());
        service.sendNotification(new WhatsappNotification());
    }
}

class NotificationSenderService {
    public void sendNotification(NotificationService notificationService) {
        notificationService.send();
    }
}

interface NotificationService {
    void send();
}

class EmailNotification implements NotificationService {

    @Override
    public void send() {
        System.out.println("Sends email notification");
    }
}

class WhatsappNotification implements NotificationService {

    @Override
    public void send() {
        System.out.println("Sends Whatsapp notification");
    }
}