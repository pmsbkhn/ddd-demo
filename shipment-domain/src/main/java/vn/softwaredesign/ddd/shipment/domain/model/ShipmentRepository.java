package vn.softwaredesign.ddd.shipment.domain.model;

import java.util.Optional;

public interface ShipmentRepository {
    Optional<Shipment> forId(ShipmentID id);

    void save(Shipment shipment);
}
