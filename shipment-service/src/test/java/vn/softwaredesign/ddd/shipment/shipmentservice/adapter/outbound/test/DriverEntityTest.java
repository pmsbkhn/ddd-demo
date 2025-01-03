package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test;

import jakarta.persistence.*;
import lombok.Data;
import vn.softwaredesign.ddd.shipment.domain.model.Driver;
import vn.softwaredesign.ddd.shipment.domain.model.DriverID;
import vn.softwaredesign.ddd.shipment.domain.model.DriverStatus;

@Entity
@Table(name = "drivers")
@Data
public class DriverEntityTest {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status;

    @Embedded
    private LocationDTO currentLocation;
    
    public Driver toDomain() {
        return new Driver(
            new DriverID(id),
            this.status,
            this.currentLocation.toDomain()
        );
    }

    public static DriverEntityTest fromDomain(Driver driver) {
        DriverEntityTest entity = new DriverEntityTest();
        entity.id = driver.id().id();
        entity.status = driver.status();
        entity.currentLocation = LocationDTO.fromDomain(driver.currentLocation());

        return entity;
    }
}
