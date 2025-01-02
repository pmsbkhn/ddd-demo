package vn.softwaredesign.ddd.shipment.application;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import vn.softwaredesign.ddd.common.domain.model.DomainEvent;
import vn.softwaredesign.ddd.common.domain.model.DomainEventPublisher;
import vn.softwaredesign.ddd.common.domain.model.DomainEventSubscriber;
import vn.softwaredesign.ddd.common.event.EventStore;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentAssigned;

@Aspect
public class ShipmentEventSubscriberProcessor {
    private final EventStore eventStore;

    public ShipmentEventSubscriberProcessor(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Before("execution(* vn.softwaredesign.ddd.shipment.domain.model.Shipment.assignDriver(..)) ||" +
            "execution(* vn.softwaredesign.ddd.shipment.domain.model.Shipment.cancelShipment(..))")
    public void register() {
        DomainEventPublisher
                .instance()
                .subscribe(new DomainEventSubscriber<ShipmentAssigned>() {
                    @Override
                    public void handleEvent(ShipmentAssigned aDomainEvent) {
                        eventStore.append((DomainEvent) aDomainEvent);
                    }

                    @Override
                    public Class<ShipmentAssigned> subscribedToEventType() {
                        return ShipmentAssigned.class;
                    }
                });
    }

}
