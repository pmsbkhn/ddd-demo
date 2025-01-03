package vn.softwaredesign.ddd.shipment;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import vn.softwaredesign.ddd.shipment.domain.model.DriverID;
import vn.softwaredesign.ddd.shipment.domain.model.Shipment;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentID;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentStatus;

import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;



@SpringBootTest(classes = ShipmentTestConfig.class)
//@DataJpaTest
//@ComponentScan(basePackages = "vn.softwaredesign.ddd.shipment.infrastructure")
@Sql(scripts = {"/schema.sql", "/data.sql"})
public class ShipmentOaTest {

    @Autowired
    private ShipmentEntityRepository shipmentEntityRepository;

    @Test
    void testSaveAndRetrieveShipment() {
        ShipmentOa shipmentOa = new ShipmentOa(shipmentEntityRepository);

        // Tạo đối tượng Shipment
        Shipment shipment = new Shipment(
                new ShipmentID("SHIP999"),
                new DriverID("DRIVER999"),
                ShipmentStatus.ASSIGNED
        );

        // Lưu Shipment
        shipmentOa.save(shipment);

        // Truy vấn lại Shipment
        Optional<Shipment> savedShipment = shipmentOa.forId(new ShipmentID("SHIP999"));
        assertTrue(savedShipment.isPresent());
        assertEquals("SHIP999", savedShipment.get().id().id());
        assertEquals("DRIVER999", savedShipment.get().driverID().id());
        assertEquals(ShipmentStatus.ASSIGNED, savedShipment.get().status());
    }
}
