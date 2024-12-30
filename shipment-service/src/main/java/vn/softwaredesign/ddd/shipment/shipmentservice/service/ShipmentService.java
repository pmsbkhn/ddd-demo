package vn.softwaredesign.ddd.shipment.shipmentservice.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.softwaredesign.ddd.shipment.application.inbound.ShipmentAssignment;
import vn.softwaredesign.ddd.shipment.application.inbound.ShipmentAssignmentInput;

@Service
public class ShipmentService {
    private final ShipmentAssignment shipmentAssignment;

    public ShipmentService(ShipmentAssignment shipmentAssignment) {
        this.shipmentAssignment = shipmentAssignment;
    }

    @Transactional
    public void findDriverAndAssign(String shipmentId) {
        ShipmentAssignmentInput input = new ShipmentAssignmentInput(shipmentId);
        shipmentAssignment.assignDriverToShipment(input);
    }
}
