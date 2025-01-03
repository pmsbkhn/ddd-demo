package vn.softwaredesign.ddd.shipment.shipmentservice.tests;


import org.junit.jupiter.api.Test;

import vn.softwaredesign.ddd.shipment.domain.model.Driver;
import vn.softwaredesign.ddd.shipment.domain.model.DriverStatus;
import vn.softwaredesign.ddd.shipment.domain.model.Shipment;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentID;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.DriverWithinDistanceSpecification;
import vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test.DriverEntityTest;
import vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test.LocationDTO;
import vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test.ShipmentEntityTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



public class RepositoriesTest extends BaseIntegrationTest {
    @Test
    void testShipmentEntityPersistence() {
        ShipmentEntityTest shipmentEntityTest = new ShipmentEntityTest("shipment1", "driver1", "ASSIGNED");
        shipmentRepository.save(shipmentEntityTest.toShipment());

        Shipment foundShipment = shipmentRepository.forId(new ShipmentID("shipment1")).orElse(null);
        assertThat(foundShipment).isNotNull();

        assert foundShipment != null;
        ShipmentEntityTest foundEntity = new ShipmentEntityTest(foundShipment);
        assertThat(foundEntity.driverID()).isEqualTo("driver1");
        assertThat(foundEntity.status()).isEqualTo("ASSIGNED");
    }
    
//    @Test
//    void testSelectDriverWithin() {
//        // Tạo dữ liệu giả lập
//        LocationDTO location1 = new LocationDTO();
//        location1.setName("Location 1");
//        location1.setLatitude(10.0);
//        location1.setLongitude(10.0);
//
//        LocationDTO location2 = new LocationDTO();
//        location2.setName("Location 2");
//        location2.setLatitude(20.0);
//        location2.setLongitude(20.0);
//
//        DriverEntityTest driver1 = new DriverEntityTest();
//        driver1.setId("driver1");
//        driver1.setStatus(DriverStatus.AVAILABLE);
//        driver1.setCurrentLocation(location1);
//
//        DriverEntityTest driver2 = new DriverEntityTest();
//        driver2.setId("driver2");
//        driver2.setStatus(DriverStatus.UNAVAILABLE);
//        driver2.setCurrentLocation(location2);
//
//        // Lưu dữ liệu giả vào repository
//        driverRepository.save(driver1.toDomain());
//        driverRepository.save(driver2.toDomain());
//
//        // Tạo specification để kiểm tra
//        DriverWithinDistanceSpecification spec = new DriverWithinDistanceSpecification(1500); // radius 1500 km
//
//        // Lấy danh sách driver phù hợp
//        Set<Driver> matchingDrivers = driverRepository.selectSatisfying(spec);
//
//        // Kiểm tra kết quả
//        assertThat(matchingDrivers).isNotNull();
//        assertThat(matchingDrivers.size()).isEqualTo(1);
//
//        Driver matchedDriver = matchingDrivers.iterator().next();
//        assertThat(matchedDriver.id().id()).isEqualTo("driver1");
//        assertThat(matchedDriver.status()).isEqualTo(DriverStatus.AVAILABLE);
//        assertThat(matchedDriver.currentLocation().name()).isEqualTo("Location 1");
//    }
}
