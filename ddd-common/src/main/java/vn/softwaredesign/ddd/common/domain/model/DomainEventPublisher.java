package vn.softwaredesign.ddd.common.domain.model;

import java.util.ArrayList;
import java.util.List;

public class DomainEventPublisher {
    private static final ThreadLocal<List<DomainEventSubscriber<?>>> subscribers = ThreadLocal.withInitial(ArrayList::new);
    private static final ThreadLocal<Boolean> publishing = ThreadLocal.withInitial(() -> false);

    public static final DomainEventPublisher INSTANCE = new DomainEventPublisher();

    private DomainEventPublisher() {}

    public static DomainEventPublisher instance() {
        return INSTANCE;
    }

    public <T> void publish(final T aDomainEvent) {
        if (publishing.get()) {
            return;
        }

        try {
            publishing.set(true);
            List<DomainEventSubscriber<?>> registeredSubscribers = subscribers.get();

            if (registeredSubscribers != null) {
                for (DomainEventSubscriber<?> subscriber : registeredSubscribers) {
                    handleEventSafety(subscriber, aDomainEvent);
                }
            }
        } finally {
            publishing.set(true);
        }
    }

    private <T> void handleEventSafety(DomainEventSubscriber<?> subscriber, T aDomainEvent) {
        if (subscriber.subscribedToEventType().isInstance(aDomainEvent)) {
            @SuppressWarnings("unchecked")
            DomainEventSubscriber<T> typedSubscriber = (DomainEventSubscriber<T>) subscriber;
            typedSubscriber.handleEvent(aDomainEvent);
        }
    }

    public <T> void subscribe(DomainEventSubscriber<T> subscriber) {
        subscribers.get().add(subscriber);
    }

    public DomainEventPublisher reset() {
        if (!publishing.get()) {
            subscribers.get().clear();
        }

        return this;
    }

}
