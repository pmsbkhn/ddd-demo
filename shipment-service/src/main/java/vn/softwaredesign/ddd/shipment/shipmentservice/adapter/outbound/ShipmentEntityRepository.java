package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ShipmentEntityRepository extends JpaRepository<ShipmentEntity, String> {
}
