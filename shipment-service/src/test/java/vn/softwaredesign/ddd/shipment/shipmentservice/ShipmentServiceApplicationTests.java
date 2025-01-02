//package vn.softwaredesign.ddd.shipment.shipmentservice;
//
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import vn.softwaredesign.ddd.common.event.EventStore;
//import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;
//import vn.softwaredesign.ddd.shipment.shipmentservice.service.ShipmentService;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@SpringBootTest
//class ShipmentServiceApplicationTests {
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Autowired
//    private ShipmentService shipmentService;
//
//    @Autowired
//    private EventStore eventStore;
//
//    @Autowired
//    private ShipmentRepository shipmentRepository;
//
//    @Test
//    @Transactional
//    void shouldAssignDriverToShipmentSuccessfully() {
//        // Arrange
//        String shipmentId = "SHIP123";
//
//        // Act
//        shipmentService.findDriverAndAssign(shipmentId);
//
//        // Assert
//        verify(eventStore, times(1)).append(any());
//    }
//
//
//}
