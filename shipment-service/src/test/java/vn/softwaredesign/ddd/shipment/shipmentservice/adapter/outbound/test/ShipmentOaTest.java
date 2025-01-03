package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test;

import vn.softwaredesign.ddd.shipment.domain.model.Shipment;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentID;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
public class ShipmentOaTest implements ShipmentRepository {

    private final ShipmentEntityRepositoryTest shipmentEntityRepositoryTest;
    
    @Autowired
    public ShipmentOaTest(ShipmentEntityRepositoryTest shipmentEntityRepositoryTest) {
        this.shipmentEntityRepositoryTest = shipmentEntityRepositoryTest;
    }

    @Override
    public Optional<Shipment> forId(ShipmentID id) {
        return shipmentEntityRepositoryTest.findById(id.id())
                .map(ShipmentEntityTest::toShipment);
    }

    @Override
    public void save(Shipment shipment) {
        ShipmentEntityTest entity = new ShipmentEntityTest(shipment);
        shipmentEntityRepositoryTest.save(entity);
    }
}
