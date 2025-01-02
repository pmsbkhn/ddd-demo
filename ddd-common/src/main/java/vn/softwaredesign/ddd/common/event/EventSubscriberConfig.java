package vn.softwaredesign.ddd.common.event;

import vn.softwaredesign.ddd.common.domain.model.DomainEvent;
import vn.softwaredesign.ddd.common.domain.model.DomainEventPublisher;
import vn.softwaredesign.ddd.common.domain.model.DomainEventSubscriber;

import java.util.List;

public abstract class EventSubscriberConfig {
    private final EventStore eventStore;

    public EventSubscriberConfig(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    private <T extends DomainEvent> void registerSubscriber(Class<T> eventType) {
        DomainEventPublisher
                .instance()
                .subscribe(new DomainEventSubscriber<T>() {
                    @Override
                    public void handleEvent(T aDomainEvent) {
                        eventStore.append((DomainEvent) aDomainEvent);
                    }

                    @Override
                    public Class<T> subscribedToEventType() {
                        return eventType;
                    }
                });
    }

    public void registerAllSubscribers() {
        List<Class<? extends DomainEvent>> events = this.registerSubscribers();

        events.forEach(this::registerSubscriber);
    }

    protected abstract List<Class<? extends DomainEvent>> registerSubscribers();
}
