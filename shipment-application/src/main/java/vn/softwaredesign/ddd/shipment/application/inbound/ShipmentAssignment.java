package vn.softwaredesign.ddd.shipment.application.inbound;

public interface ShipmentAssignment {
    void assignDriverToShipment(ShipmentAssignmentInput input);
}
