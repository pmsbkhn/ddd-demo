package vn.softwaredesign.ddd.shipment;


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
public class ShipmentEntity {
    @Id
    private String id;

    @Column(name = "driver_id")
    private String driverID;

    @Column
    private String status;

    public ShipmentEntity() {}

    public ShipmentEntity(String id, String driverID, String status) {
        this.id = id;
        this.driverID = driverID;
        this.status = status;
    }

    public ShipmentEntity(Shipment shipment) {
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

}
