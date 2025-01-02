package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.softwaredesign.ddd.shipment.domain.model.Shipment;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentID;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;

import java.util.Optional;

@Component
public class ShipmentOa implements ShipmentRepository {

    private final ShipmentEntityRepository shipmentEntityRepository;

    @Autowired
    public ShipmentOa(ShipmentEntityRepository shipmentEntityRepository) {
        this.shipmentEntityRepository = shipmentEntityRepository;
    }

    @Override
    public Optional<Shipment> forId(ShipmentID id) {
        return shipmentEntityRepository.findById(id.id())
                .map(ShipmentEntity::toShipment);
    }

    @Override
    public void save(Shipment shipment) {
        ShipmentEntity entity = new ShipmentEntity(shipment);
        shipmentEntityRepository.save(entity);
    }
}
