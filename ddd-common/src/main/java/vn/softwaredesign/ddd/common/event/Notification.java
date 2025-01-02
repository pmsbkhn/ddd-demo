package vn.softwaredesign.ddd.common.event;

import vn.softwaredesign.ddd.common.AssertionConcern;
import vn.softwaredesign.ddd.common.domain.model.DomainEvent;

import java.time.LocalDateTime;

public class Notification extends AssertionConcern {
    private DomainEvent domainEvent;
    private long notificationId;
    private LocalDateTime occurredOn;
    private String typeName;
    private int version;

    public void setDomainEvent(DomainEvent domainEvent) {
        this.domainEvent = domainEvent;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }

    public void setOccurredOn(LocalDateTime occurredOn) {
        this.occurredOn = occurredOn;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String type() {
        return typeName;
    }

    public long notificationId() {
        return notificationId;
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    public DomainEvent domainEvent() {
        return domainEvent;
    }

    public int version() {
        return version;
    }
}
