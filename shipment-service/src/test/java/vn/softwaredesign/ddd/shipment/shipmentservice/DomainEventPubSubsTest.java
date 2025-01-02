//package vn.softwaredesign.ddd.shipment.shipmentservice;
//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import vn.softwaredesign.ddd.common.event.EventStore;
//import vn.softwaredesign.ddd.shipment.domain.model.ShipmentAssigned;
//import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;
//import vn.softwaredesign.ddd.shipment.shipmentservice.service.ShipmentService;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//@ActiveProfiles("test")
//public class DomainEventPubSubsTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
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
//    void testAssignDriverEvent() {
//        // Arrange
//        String shipmentId = "123"; // ID giả lập
//
//        // Act
//        ResponseEntity<String> response = restTemplate.postForEntity(
//                "/api/shipments/{shipmentId}/assign-driver",
//                null,
//                String.class,
//                shipmentId
//        );
//
//        // Assert
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//        assertThat(response.getBody()).contains("Driver assigned successfully to shipment ID: " + shipmentId);
//
//        long eventCount = eventStore.countStoredEvents();
//        assertThat(eventCount).isEqualTo(1);
//
//        assertThat(eventStore.allStoredEventsSince(0L))
//                .extracting(Object::getClass)
//                .isEqualTo(ShipmentAssigned.class);
//
//    }
//
//}
