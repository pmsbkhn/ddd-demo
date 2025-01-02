//package vn.softwaredesign.ddd.shipment;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.jdbc.Sql;
//
//import static junit.framework.Assert.assertEquals;
//
//@DataJpaTest
//@Sql(scripts = "/data.sql")
//public class ShipmentEntityTest {
//
//    @Autowired
//    private ShipmentEntityRepository shipmentEntityRepository;
//
//    @Test
//    void testShipmentEntitySaveAndFetch() {
//        ShipmentEntity shipment = new ShipmentEntity(
//                "SHIP123",
//                "Standard",
//                10.7769,
//                106.7009,
//                10.7625,
//                106.6822,
//                "DRIVER123",
//                "ASSIGNED"
//        );
//
//        shipmentEntityRepository.save(shipment);
//
//        ShipmentEntity fetchedShipment = shipmentEntityRepository.findById("SHIP123")
//                .orElseThrow(() -> new IllegalArgumentException("Shipment not found"));
//
//        assertEquals("SHIP123", fetchedShipment.getId());
//        assertEquals("DRIVER123", fetchedShipment.getDriverID());
//        assertEquals("ASSIGNED", fetchedShipment.getStatus());
//    }
//}
