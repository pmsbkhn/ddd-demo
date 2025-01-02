package vn.softwaredesign.ddd.event.springmechanism.messaging;

import vn.softwaredesign.ddd.common.event.Notification;

public class NotificationAvroMessage {
    private String notificationId;
    private String typeName;
    private String occurredOn;
    private String domainEvent;
    private int version;

    // Constructor
    public NotificationAvroMessage(String notificationId, String typeName, String occurredOn, String domainEvent, int version) {
        this.notificationId = notificationId;
        this.typeName = typeName;
        this.occurredOn = occurredOn;
        this.domainEvent = domainEvent;
        this.version = version;
    }

    // Factory method for conversion from Notification
    public static NotificationAvroMessage fromNotification(Notification notification) {
        return new NotificationAvroMessage(
                String.valueOf(notification.notificationId()),
                notification.type(),
                notification.occurredOn().toString(),
                notification.domainEvent().toString(),
                notification.version()
        );
    }

    public String notificationId() {
        return notificationId;
    }

    public String typeName() {
        return typeName;
    }

    public String occurredOn() {
        return occurredOn;
    }

    public String domainEvent() {
        return domainEvent;
    }

    public int version() {
        return version;
    }
}
