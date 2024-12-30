package vn.softwaredesign.ddd.shipment.application.usecases;

import vn.softwaredesign.ddd.common.domain.model.DomainEventPublisher;
import vn.softwaredesign.ddd.common.domain.model.DomainEventSubscriber;
import vn.softwaredesign.ddd.common.event.EventStore;
import vn.softwaredesign.ddd.shipment.application.inbound.ShipmentAssignment;
import vn.softwaredesign.ddd.shipment.application.inbound.ShipmentAssignmentInput;
import vn.softwaredesign.ddd.shipment.domain.model.*;

public class ShipmentAssignmentUseCase implements ShipmentAssignment {
    private final ShipmentRepository shipmentRepository;
    private final FindDriverForShipmentService findDriverForShipmentService;

    public ShipmentAssignmentUseCase(ShipmentRepository shipmentRepository,
                                     FindDriverForShipmentService findDriverForShipmentService,
                                     EventStore eventStore) {
        this.shipmentRepository = shipmentRepository;
        this.findDriverForShipmentService = findDriverForShipmentService;
    }

    @Override
    public void assignDriverToShipment(ShipmentAssignmentInput input) {

//        DomainEventPublisher.instance().reset();
//
//        DomainEventSubscriber<ShipmentAssigned> domainEventSubscriber = new DomainEventSubscriber<ShipmentAssigned>() {
//            @Override
//            public void handleEvent(ShipmentAssigned aDomainEvent) {
//                eventStore.append(aDomainEvent);
//            }
//
//            @Override
//            public Class<ShipmentAssigned> subscribedToEventType() {
//                return ShipmentAssigned.class;
//            }
//        };
//
//        DomainEventPublisher.instance().subscribe(domainEventSubscriber);

        ShipmentID shipmentID = new ShipmentID(input.shipmentId());
        Shipment shipment = this.shipmentRepository.forId(shipmentID)
                .orElseThrow(() -> new IllegalArgumentException("No shipment found for ID: " + shipmentID));

        Driver driver = findDriverForShipmentService.findDriver(shipment);
        shipment.assignDriver(driver);

        shipmentRepository.save(shipment);
    }
}
