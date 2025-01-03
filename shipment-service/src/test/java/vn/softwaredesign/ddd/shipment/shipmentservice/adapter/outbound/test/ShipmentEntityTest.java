package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import vn.softwaredesign.ddd.shipment.domain.model.DriverID;
import vn.softwaredesign.ddd.shipment.domain.model.Shipment;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentID;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentStatus;

@Entity
@Table(name = "shipments")
@Data
public class ShipmentEntityTest {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(name = "driver_id")
    private String driverID;

    @Column(nullable = false)
    private String status;

    public ShipmentEntityTest() {}

    public ShipmentEntityTest(String id,
                              String driverID,
                              String status) {
        this.id = id;
        this.driverID = driverID;
        this.status = status;
    }

    public ShipmentEntityTest(Shipment shipment) {
        this.id = shipment.id().id();
        this.driverID = shipment.driverID().id();
        this.status = shipment.status().name();
    }

    public Shipment toShipment() {
        return new Shipment(
                new ShipmentID(this.id),
                new DriverID(this.driverID),
                ShipmentStatus.valueOf(this.status)
        );
    }

    public String id() {
        return id;
    }

    public String status() {
        return status;
    }

    public String driverID() {
        return driverID;
    }
}
