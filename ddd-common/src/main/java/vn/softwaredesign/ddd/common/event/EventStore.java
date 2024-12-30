package vn.softwaredesign.ddd.common.event;

import vn.softwaredesign.ddd.common.domain.model.DomainEvent;

import java.util.List;

public interface EventStore {
    StoredEvent append(DomainEvent aDomainEvent);

    void close();

    long countStoredEvents();

    List<StoredEvent> allStoredEventsSince(long aStoredEventId);

    List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId);
}
