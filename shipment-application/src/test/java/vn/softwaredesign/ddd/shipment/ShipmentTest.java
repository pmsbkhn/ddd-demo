package vn.softwaredesign.ddd.shipment;

public class ShipmentTest {
//    private Shipment shipment;
//    private Driver mockDriver;
//    private DomainEventPublisher mockPublisher;
//
//    @BeforeEach
//    void setUp() {
//        // Mock các đối tượng
//        shipment = new Shipment(new ShipmentID("SHIP123"));
//        mockDriver = mock(Driver.class);
//        mockPublisher = mock(DomainEventPublisher.class);
//
//        // Giả lập hành vi cho driver
//        when(mockDriver.id()).thenReturn(new DriverID("DRIVER123"));
//
//        // Thay thế singleton DomainEventPublisher bằng mock
//        DomainEventPublisher.INSTANCE.reset();
//    }
//
//    @Test
//    void testAssignDriver() {
//        // Gán tài xế cho shipment
//        shipment.assignDriver(mockDriver);
//
//        // ✅ Kiểm tra DriverID đã được gán đúng
//        assertEquals("DRIVER123", shipment.driverID().id(),
//                "DriverID should match the assigned driver's ID");
//
//        // ✅ Kiểm tra trạng thái đã được chuyển thành ASSIGNED
//        assertEquals("Shipment status should be ASSIGNED after assigning a driver", ShipmentStatus.ASSIGNED,
//                shipment.status());
//
//        // ✅ Kiểm tra sự kiện ShipmentAssigned đã được phát
//        // Sử dụng ArgumentCaptor để kiểm tra sự kiện
//        ArgumentCaptor<ShipmentAssigned> eventCaptor = ArgumentCaptor.forClass(ShipmentAssigned.class);
//        DomainEventPublisher.instance().publish(eventCaptor.capture());
//
//        ShipmentAssigned event = eventCaptor.getValue();
//        assertEquals("SHIP123", event.shipmentID().id(), "Shipment ID in event should match the assigned shipment");
//        assertEquals("DRIVER123", event.driverID().id(), "Driver ID in event should match the assigned driver");
//        assertNotNull(event.occurredOn(), "Event should have a valid occurredOn timestamp");
//    }
}
