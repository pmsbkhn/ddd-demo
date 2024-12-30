package vn.softwaredesign.ddd.shipment.application.inbound;


public class ShipmentAssignmentInput {
    private final String shipmentId;

    public ShipmentAssignmentInput(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String shipmentId() {
        return shipmentId;
    }
}
