package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound;

import org.springframework.stereotype.Component;
import vn.softwaredesign.ddd.shipment.domain.model.Shipment;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentID;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;

import java.util.Optional;


@Component
public class ShipmentOutputAdapter implements ShipmentRepository {
    @Override
    public Optional<Shipment> forId(ShipmentID id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public void save(Shipment shipment) {
        // TODO
    }
}
