package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.softwaredesign.ddd.common.event.EventStore;
import vn.softwaredesign.ddd.shipment.application.ShipmentEventSubscriberProcessor;

@Component
public class ShipmentEventSubscriberAdapter {

    private final ShipmentEventSubscriberProcessor shipmentEventSubscriberProcessor;

    public ShipmentEventSubscriberAdapter(ShipmentEventSubscriberProcessor shipmentEventSubscriberProcessor) {
        this.shipmentEventSubscriberProcessor = shipmentEventSubscriberProcessor;
    }

    @PostConstruct
    public void initializeSubscribers() {
        shipmentEventSubscriberProcessor.register();
    }

}
