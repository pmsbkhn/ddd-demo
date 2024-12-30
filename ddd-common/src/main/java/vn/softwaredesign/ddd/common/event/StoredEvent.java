package vn.softwaredesign.ddd.common.event;

import vn.softwaredesign.ddd.common.AssertionConcern;
import vn.softwaredesign.ddd.common.domain.model.DomainEvent;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class StoredEvent extends AssertionConcern {
    private String eventBody;
    private long eventId;
    private LocalDateTime occurredOn;
    private String typeName;

    public StoredEvent() {
        super();

        this.setEventId(-1);
    }

    public StoredEvent(Long eventId,
                       String aTypeName,
                       LocalDateTime anOccurredOn,
                       String anEventBody) {
        this();

        this.setEventId(eventId);
        this.setEventBody(anEventBody);
        this.setOccurredOn(anOccurredOn);
        this.setTypeName(aTypeName);
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass;
        try {
            domainEventClass = (Class<T>) Class.forName(this.typeName());
        } catch (Exception e) {
            throw new IllegalStateException("Class load error, because: " + e.getMessage());
        }

        return EventSerializer
                .instance()
                .deserialize(this.eventBody(), domainEventClass);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StoredEvent that = (StoredEvent) o;
        return eventId == that.eventId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(eventId);
    }

    @Override
    public String toString() {
        return "StoredEvent{" +
                "eventBody='" + eventBody + '\'' +
                ", eventId=" + eventId +
                ", occurredOn=" + occurredOn +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public String eventBody() {
        return eventBody;
    }

    public long eventId() {
        return eventId;
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    public String typeName() {
        return typeName;
    }

    protected void setEventId(long anEventId) {
        this.eventId = anEventId;
    }

    protected void setEventBody(String anEventBody) {
        this.assertArgumentNotEmpty(anEventBody, "The event body is required.");
        this.assertArgumentLength(anEventBody, 1, 65000, "The event body must be 65000 characters or less.");

        this.eventBody = anEventBody;
    }

    protected void setOccurredOn(LocalDateTime anOccurredOn) {
        this.occurredOn = anOccurredOn;
    }

    protected void setTypeName(String aTypeName) {
        this.assertArgumentNotEmpty(aTypeName, "The event type name is required.");
        this.assertArgumentLength(aTypeName, 1, 100, "The event type name must be 100 characters or less.");

        this.typeName = aTypeName;
    }
}
