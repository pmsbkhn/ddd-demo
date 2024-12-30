package vn.softwaredesign.ddd.event.springmechanism.persistence;


import jakarta.persistence.*;
import vn.softwaredesign.ddd.common.event.StoredEvent;

import java.time.LocalDateTime;

@Entity
@Table(name = "stored_events")
public class StoredEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private Long eventId;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String eventBody;

    @Column(nullable = false)
    private LocalDateTime occurredOn;

    public StoredEventEntity(StoredEvent storedEvent) {
        this.eventId = storedEvent.eventId();
        this.eventType = storedEvent.typeName();
        this.eventBody = storedEvent.eventBody();
        this.occurredOn = LocalDateTime.now(); // Lấy theo timezone hệ thống
    }

    public StoredEvent toStoredEvent() {
        return new StoredEvent(
                this.eventId,
                this.eventType,
                this.occurredOn,
                this.eventBody
        );
    }

    protected StoredEventEntity() {}

    public Long id() {
        return id;
    }

    public Long eventId() {
        return eventId;
    }

    public String eventType() {
        return eventType;
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    public String eventBody() {
        return eventBody;
    }
}
