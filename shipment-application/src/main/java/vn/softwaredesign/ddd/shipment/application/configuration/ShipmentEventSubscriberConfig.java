package vn.softwaredesign.ddd.shipment.application.configuration;

import vn.softwaredesign.ddd.common.domain.model.DomainEvent;
import vn.softwaredesign.ddd.common.event.EventStore;
import vn.softwaredesign.ddd.common.event.EventSubscriberConfig;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentAssigned;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentCancelled;

import java.util.Arrays;
import java.util.List;

public class ShipmentEventSubscriberConfig extends EventSubscriberConfig {

    public ShipmentEventSubscriberConfig(EventStore eventStore) {
        super(eventStore);
    }

    @Override
    protected List<Class<? extends DomainEvent>> registerSubscribers() {
        return Arrays.asList(
                ShipmentAssigned.class,
                ShipmentCancelled.class
        );
    }
}
