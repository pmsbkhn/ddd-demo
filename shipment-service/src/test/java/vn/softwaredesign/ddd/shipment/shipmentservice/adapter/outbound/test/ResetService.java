package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResetService {
	
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public void resetDatabase() {
        // Disable referential integrity
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        // Truncate tables
        entityManager.createNativeQuery("TRUNCATE TABLE shipments").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE drivers").executeUpdate();

        // Re-enable referential integrity
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
