package vn.softwaredesign.ddd.shipment.shipmentservice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import vn.softwaredesign.ddd.shipment.domain.model.Shipment;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentID;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;
import vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.ShipmentEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@ActiveProfiles("test")
public class ApplicationContextTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Test
    void testShipmentEntityPersistence() {
        ShipmentEntity shipmentEntity = new ShipmentEntity("shipment1", "driver1", "ASSIGNED");
        shipmentRepository.save(shipmentEntity.toShipment());

        Shipment foundShipment = shipmentRepository.forId(new ShipmentID("shipment1")).orElse(null);
        assertThat(foundShipment).isNotNull();

        assert foundShipment != null;
        ShipmentEntity foundEntity = new ShipmentEntity(foundShipment);
        assertThat(foundEntity.driverID()).isEqualTo("driver1");
        assertThat(foundEntity.status()).isEqualTo("ASSIGNED");
    }
}
