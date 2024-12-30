package vn.softwaredesign.ddd.shipment.domain.model;

import vn.softwaredesign.ddd.common.domain.model.DomainEventPublisher;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.DriverSpecification;

import java.time.LocalDateTime;

public class Shipment {
    private ShipmentID id;
    private ShipmentFeature shipmentFeature;
    private double pickupLatitude;
    private double pickupLongitude;
    private double deliveryLatitude;
    private double deliveryLongitude;
    private DriverID driverID;
    private ShipmentStatus status;

    private DriverSpecification driverSpecification;

    public void assignDriver(Driver driver) {
        driverID = driver.id();
        status = ShipmentStatus.ASSIGNED;
        // Generate domain event: ShipmentAssigned
        DomainEventPublisher
                .instance()
                .publish(new ShipmentAssigned(
                        this.id,
                        driverID,
                        LocalDateTime.now()
                ));
    }

    public DriverSpecification driverSpecification() {
        return this.driverSpecification;
    }

    public void setDriverSpecification(DriverSpecification aDriverSpecification) {
        this.driverSpecification = aDriverSpecification;
    }
}
