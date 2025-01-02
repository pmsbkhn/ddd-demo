package vn.softwaredesign.ddd.shipment.domain.model;

import vn.softwaredesign.ddd.common.domain.model.DomainEvent;

import java.time.LocalDateTime;


public class ShipmentAssigned implements DomainEvent {

    private final ShipmentID shipmentID;
    private final DriverID driverID;
    private final LocalDateTime occurredOn;
    private final int eventVersion;

    public ShipmentAssigned(ShipmentID shipmentID,
                            DriverID driverID,
                            LocalDateTime occurredOn) {
        this.shipmentID = shipmentID;
        this.driverID = driverID;
        this.occurredOn = occurredOn;
        this.eventVersion = 0;
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

    public DriverID driverID() {
        return driverID;
    }
}
