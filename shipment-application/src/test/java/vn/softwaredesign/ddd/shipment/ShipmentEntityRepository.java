package vn.softwaredesign.ddd.shipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentEntityRepository extends JpaRepository<ShipmentEntity, String> {
}
