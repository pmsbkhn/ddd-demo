package vn.softwaredesign.ddd.common.event;

import vn.softwaredesign.ddd.common.domain.model.DomainEvent;
import vn.softwaredesign.ddd.common.domain.model.DomainEventSubscriber;

public class EventStoreSubscriber implements DomainEventSubscriber<DomainEvent> {

    private final EventStore eventStore;

    public EventStoreSubscriber(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void handleEvent(DomainEvent aDomainEvent) {
        eventStore.append(aDomainEvent);
    }

    @Override
    public Class<DomainEvent> subscribedToEventType() {
        return DomainEvent.class;
    }
}
