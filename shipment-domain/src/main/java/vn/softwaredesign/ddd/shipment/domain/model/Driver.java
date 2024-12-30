package vn.softwaredesign.ddd.shipment.domain.model;

import vn.softwaredesign.ddd.shipment.domain.model.specifications.Location;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.Route;

import java.util.Set;

public class Driver {
    private DriverID id;
    private DriverStatus status;
    private double currentLoad;
    private double capacity;
    private Set<ShipmentFeature> preferredShipmentFeatures;
    private Location currentLocation;
    private Route route;

    public Boolean inStatus(DriverStatus requiredStatus) {
        return status == requiredStatus;
    }

    public boolean canAccommodateShipment(Shipment aShipment) {
        return hasCapacityForLoad(aShipment) && aShipment.driverSpecification().matches(this);
    }

    private boolean hasCapacityForLoad(Shipment aShipment) {
        // TODO
        return false;
    }

    public DriverID id() {
        return id;
    }
}
