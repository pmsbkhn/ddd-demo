package vn.softwaredesign.ddd.shipment.domain.model;

import vn.softwaredesign.ddd.common.domain.model.DomainEvent;

import java.time.LocalDateTime;

public class ShipmentCancelled implements DomainEvent {

    private final ShipmentID shipmentID;
    private final LocalDateTime occurredOn;
    private final int eventVersion;

    public ShipmentCancelled(ShipmentID shipmentID, LocalDateTime occurredOn, int eventVersion) {
        this.shipmentID = shipmentID;
        this.occurredOn = occurredOn;
        this.eventVersion = eventVersion;
    }

    @Override
    public int eventVersion() {
        return eventVersion;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    public ShipmentID shipmentID() {
        return shipmentID;
    }
}
